package edu.upc.caminstech.equipstic.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.Campus;
import edu.upc.caminstech.equipstic.Categoria;
import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.SistemaOperatiu;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.TipusUs;
import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.Unitat;
import edu.upc.caminstech.equipstic.UsuariInfraestructura;

/**
 * Una implementació per defecte del client.
 * <p>
 * Molt probablement preferiu la versió amb <em>caché</em>
 * {@link EquipsTicClientSpringCachedImpl}.
 * <p>
 * Exemple d'utilització:
 * <p>
 * <code>
 * URI uri = URI.create("https://example.com/paht_to_api"); //veure manual SOA <br>
 * EquipsTicClientImpl client = new EquipsTicClientImpl(uri, "soa_user", "soa_password")); <br>
 * List&lt;Campus&gt; campus = client.getCampus();
 * </code>
 * 
 * @see EquipsTicClient
 */
public class EquipsTicClientImpl implements EquipsTicClient {

    /**
     * El TimeZone que fa servir el servidor d'EquipsTIC.
     */
    private static final TimeZone EQUIPSTIC_SERVER_TIMEZONE = TimeZone.getTimeZone("Europe/Madrid");

    private final String baseUri;
    private final RestTemplate restTemplate;

    /**
     * Crea una nova instància del client.
     * <p>
     * El client retornat codifica/descodifica les dates fent servir el TimeZone
     * que utilitza el servidor EquipsTIC de la UPC. Si voleu configurar un
     * altre {@code TimeZone}, feu servir el
     * {@link #EquipsTicClientImpl(URI, String, String, TimeZone) constructor
     * alternatiu}.
     *
     * @param baseUri
     *            la URL de la API, tal com indica la documentació del bus SOA.
     *            Es pot fer servir tant la URL de desenvolupament com la de
     *            producció.
     * @param username
     *            el nostre usuari al bus SOA (ha de tenir accés a la API).
     * @param password
     *            la nostra contrasenya al bus SOA.
     */
    public EquipsTicClientImpl(URI baseUri, String username, String password) {
        this(baseUri, username, password, EQUIPSTIC_SERVER_TIMEZONE);
    }

    /**
     * Crea una nova instància del client.
     *
     * @param baseUri
     *            la URL de la API, tal com indica la documentació del bus SOA.
     *            Es pot fer servir tant la URL de desenvolupament com la de
     *            producció.
     * @param username
     *            el nostre usuari al bus SOA (ha de tenir accés a la API).
     * @param password
     *            la nostra contrasenya al bus SOA.
     * @param timeZone
     *            el {@code TimeZone} que fa servir el servidor d'EquipsTIC.
     */
    public EquipsTicClientImpl(URI baseUri, String username, String password, TimeZone timeZone) {
        this.baseUri = baseUri.toString();
        HttpClient httpClient = prepareHttpClient(baseUri, username, password);
        restTemplate = prepareRestTemplate(httpClient, timeZone);
    }

    /**
     * Mètode auxiliar per instanciar un HttpClient a partir de les credencials
     * d'autenticació.
     */
    private HttpClient prepareHttpClient(URI baseUri, String username, String password) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(baseUri.getHost(), baseUri.getPort());
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        credsProvider.setCredentials(authScope, credentials);
        HttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

        return httpClient;
    }

    private RestTemplate prepareRestTemplate(HttpClient httpClient, TimeZone timeZone) {
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        fixSupportedMediaTypes(template);
        fixJacksonObjectMapperTimezone(template, timeZone);
        return template;
    }

    /**
     * Reconfigura la serialització/deserialització amb Jackson per tenir en
     * compte implícitament el TimeZone indicat.
     * 
     * Això és necessari perquè Jackson per defecte fa servir el timezone UTC
     * quan no s'explicita el TimeZone, però sembla que el servidor EquipsTIC fa
     * servir CET ("Europe/Madrid" o equivalent).
     */
    private void fixJacksonObjectMapperTimezone(RestTemplate template, TimeZone timeZone) {
        Optional<HttpMessageConverter<?>> converter = template.getMessageConverters().stream()
                .filter(c -> c.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()))
                .findFirst();
        if (converter.isPresent()) {
            MappingJackson2HttpMessageConverter c = (MappingJackson2HttpMessageConverter) converter.get();
            c.getObjectMapper().setTimeZone(timeZone);
        }
    }

    /**
     * Reconfigura els Converters de la RestTemplate per tal que acceptin també
     * respostes de tipus "text/plain".
     *
     * Això és necessari perquè el servidor SOA retorna erròniament un
     * Content-Type amb valor "text/plain" quan hauria de ser
     * "application/json".
     */
    private void fixSupportedMediaTypes(RestTemplate restTemplate) {
        for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setObjectMapper(new ObjectMapper());
                jsonConverter.setSupportedMediaTypes(Arrays.asList(
                        new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET),
                        new MediaType("text", "plain", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
            }
        }
    }

    @Override
    public List<Ambit> getAmbits() {
        List<Ambit> result = get("/ambit", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        });
        return sorted(result);
    }

    @Override
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        if (nomAmbit == null) {
            throw new IllegalArgumentException("El nom de l'àmbit no pot ser null");
        }
        List<Ambit> result = get("/ambit/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        }, nomAmbit);
        return sorted(result);
    }

    @Override
    public Ambit getAmbitById(long idAmbit) {
        return get("/ambit/{id}", new ParameterizedTypeReference<Response<Ambit>>() {
        }, idAmbit);
    }

    @Override
    public List<Campus> getCampus() {
        List<Campus> result = get("/campus", new ParameterizedTypeReference<Response<List<Campus>>>() {
        });
        return sorted(result);
    }

    @Override
    public Campus getCampusByCodi(String codiCampus) {
        if (codiCampus == null) {
            throw new IllegalArgumentException("El codi del campus no pot ser null");
        }
        return get("/campus/cerca/codi/{codi}", new ParameterizedTypeReference<Response<Campus>>() {
        }, codiCampus);
    }

    @Override
    public Campus getCampusById(long idCampus) {
        return get("/campus/{id}", new ParameterizedTypeReference<Response<Campus>>() {
        }, idCampus);
    }

    @Override
    public List<Categoria> getCategories() {
        List<Categoria> result = get("/categoria", new ParameterizedTypeReference<Response<List<Categoria>>>() {
        });
        return sorted(result);
    }

    @Override
    public Categoria getCategoriaById(long idCategoria) {
        return get("/categoria/{id}", new ParameterizedTypeReference<Response<Categoria>>() {
        }, idCategoria);
    }

    @Override
    public List<Edifici> getEdificis() {
        List<Edifici> result = get("/edifici", new ParameterizedTypeReference<Response<List<Edifici>>>() {
        });
        return sorted(result);
    }

    @Override
    public Edifici getEdificiById(long idEdifici) {
        return get("/edifici/{id}", new ParameterizedTypeReference<Response<Edifici>>() {
        }, idEdifici);
    }

    @Override
    public Edifici getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus) {
        if (codiEdifici == null) {
            throw new IllegalArgumentException("El codi de l'edifici no pot ser null");
        }
        if (codiCampus == null) {
            throw new IllegalArgumentException("El codi del campus no pot ser null");
        }
        return get("/edifici/cerca/codi/{codi}/codicampus/{codiCampus}",
                new ParameterizedTypeReference<Response<Edifici>>() {
                }, codiEdifici, codiCampus);
    }

    @Override
    public List<Estat> getEstats() {
        List<Estat> result = get("/estat", new ParameterizedTypeReference<Response<List<Estat>>>() {
        });
        return sorted(result);
    }

    @Override
    public Estat getEstatByCodi(String codiEstat) {
        if (codiEstat == null) {
            throw new IllegalArgumentException("El codi de l'estat no pot ser null");
        }
        return get("/estat/cerca/codi/{codi}", new ParameterizedTypeReference<Response<Estat>>() {
        }, codiEstat);
    }

    @Override
    public List<Estat> getEstatsByNom(String nomEstat) {
        if (nomEstat == null) {
            throw new IllegalArgumentException("El nom de l'estat no pot ser null");
        }
        List<Estat> result = get("/estat/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Estat>>>() {
        }, nomEstat);
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    public Estat getEstatById(long idEstat) {
        return get("/estat/{id}", new ParameterizedTypeReference<Response<Estat>>() {
        }, idEstat);
    }

    @Override
    public List<Marca> getMarques() {
        List<Marca> result = get("/marca", new ParameterizedTypeReference<Response<List<Marca>>>() {
        });
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    public List<Marca> getMarquesByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la marca no pot ser null");
        }
        List<Marca> result = get("/marca/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Marca>>>() {
        }, nom);
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    public Marca getMarcaById(long idMarca) {
        return get("/marca/{id}", new ParameterizedTypeReference<Response<Marca>>() {
        }, idMarca);
    }

    @Override
    public List<TipusUs> getTipusUs() {
        List<TipusUs> result = get("/tipusUs", new ParameterizedTypeReference<Response<List<TipusUs>>>() {
        });
        return sorted(result);
    }

    @Override
    public List<TipusUs> getTipusUsByUnitat(long idUnitat) {
        List<TipusUs> result = get("/tipusUs/cerca/unitat/{idUnitat}",
                new ParameterizedTypeReference<Response<List<TipusUs>>>() {
                }, idUnitat);
        return sorted(result);
    }

    @Override
    public TipusUs getTipusUsById(long idTipusUs) {
        return get("/tipusUs/{idTipusUs}", new ParameterizedTypeReference<Response<TipusUs>>() {
        }, idTipusUs);
    }

    @Override
    public List<TipusInfraestructura> getTipusInfraestructura() {
        List<TipusInfraestructura> result = get("/tipusInfraestructura",
                new ParameterizedTypeReference<Response<List<TipusInfraestructura>>>() {
                });
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    public List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria) {
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/categoria/{idCategoria}",
                new ParameterizedTypeReference<Response<List<TipusInfraestructura>>>() {
                }, idCategoria);
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    public TipusInfraestructura getTipusInfraestructuraBycodi(String codi) {
        if (codi == null) {
            throw new IllegalArgumentException("El codi del tipus no pot ser null");
        }
        return get("/tipusInfraestructura/cerca/codi/{codi}",
                new ParameterizedTypeReference<Response<TipusInfraestructura>>() {
                }, codi);
    }

    @Override
    public List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom del tipus no pot ser null");
        }
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/nom/{nom}",
                new ParameterizedTypeReference<Response<List<TipusInfraestructura>>>() {
                }, nom);
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    public TipusInfraestructura getTipusInfraestructuraById(long idTipus) {
        return get("/tipusInfraestructura/{id}", new ParameterizedTypeReference<Response<TipusInfraestructura>>() {
        }, idTipus);
    }

    @Override
    public List<TipusXarxa> getTipusXarxa() {
        List<TipusXarxa> result = get("/tipusXarxa", new ParameterizedTypeReference<Response<List<TipusXarxa>>>() {
        });
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    public TipusXarxa getTipusXarxaById(long idTipusXarxa) {
        return get("/tipusXarxa/{id}", new ParameterizedTypeReference<Response<TipusXarxa>>() {
        }, idTipusXarxa);
    }

    @Override
    public List<Unitat> getUnitats() {
        List<Unitat> result = get("/unitat", new ParameterizedTypeReference<Response<List<Unitat>>>() {
        });
        return sorted(result);
    }

    @Override
    public Unitat getUnitatByIdentificador(String identificador) {
        if (identificador == null) {
            throw new IllegalArgumentException("L'identificador de la unitat no pot ser null");
        }

        return get("/unitat/cerca/identificador/{identificador}", new ParameterizedTypeReference<Response<Unitat>>() {
        }, identificador);
    }

    @Override
    public UsuariInfraestructura getUsuariInfraestructura(long idUsuariInfraestructura) {
        return get("/usuariInfraestructura/{idUsuariInfraestructura}",
                new ParameterizedTypeReference<Response<UsuariInfraestructura>>() {
                }, idUsuariInfraestructura);
    }

    @Override
    public List<UsuariInfraestructura> getUsuarisInfraestructura() {
        List<UsuariInfraestructura> result = get("/usuariInfraestructura",
                new ParameterizedTypeReference<Response<List<UsuariInfraestructura>>>() {
                });
        return sorted(result);
    }

    @Override
    public List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de l'usuari no pot ser null");
        }
        List<UsuariInfraestructura> result = get("/usuariInfraestructura/cerca/nom/{nom}",
                new ParameterizedTypeReference<Response<List<UsuariInfraestructura>>>() {
                }, nom);
        return sorted(result);
    }

    @Override
    public List<Unitat> getUnitatsByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la unitat no pot ser null");
        }
        List<Unitat> result = get("/unitat/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Unitat>>>() {
        }, nom);
        return sorted(result);
    }

    @Override
    public List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la unitat no pot ser null");
        }
        if (identificador == null) {
            throw new IllegalArgumentException("L'identificador de la unitat no pot ser null");
        }
        if (codiUnitat == null) {
            throw new IllegalArgumentException("El codi de la unitat no pot ser null");
        }

        List<Unitat> result = get("/unitat/cerca/nom/{nom}/identificador/{identificador}/codi/{codi}",
                new ParameterizedTypeReference<Response<List<Unitat>>>() {
                }, nom, identificador, codiUnitat);

        return sorted(result);
    }

    @Override
    public Unitat getUnitatById(long idUnitat) {
        return get("/unitat/{id}", new ParameterizedTypeReference<Response<Unitat>>() {
        }, idUnitat);
    }

    @Override
    public Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn) {
        if (sn == null) {
            throw new IllegalArgumentException("El número de sèrie no pot ser null");
        }
        Infraestructura i = get("/infraestructura/cerca/marca/{idMarca}/sn/{sn}",
                new ParameterizedTypeReference<Response<Infraestructura>>() {
                }, idMarca, sn);
        ompleCampsNoInicialitzatsInfraestructura(i);
        return i;
    }

    @Override
    public Infraestructura getInfraestructuraById(long id) {
        Infraestructura i = get("/infraestructura/{id}", new ParameterizedTypeReference<Response<Infraestructura>>() {
        }, id);
        ompleCampsNoInicialitzatsInfraestructura(i);
        return i;
    }

    /**
     * Inicialitza els atributs d'una infraestructura que corresponen a objectes
     * JSON que només tenen inicialitzat l'identificador.
     * 
     * @param infra
     *            la infraestructura
     */
    private void ompleCampsNoInicialitzatsInfraestructura(Infraestructura infra) {
        if (infra == null) {
            return;
        }
        Marca marca = getMarcaById(infra.getMarca().getIdMarca());
        TipusInfraestructura tipusInfraestructura = getTipusInfraestructuraById(
                infra.getTipusInfraestructura().getIdTipus());
        Estat estat = getEstatById(infra.getEstat().getIdEstat());
        Unitat unitat = getUnitatById(infra.getUnitat().getIdUnitat());
        Edifici edifici = getEdificiById(infra.getEdifici().getIdEdifici());
        Estat estatValidacio = getEstatById(infra.getEstatValidacio().getIdEstat());
        Unitat unitatGestora = getUnitatById(infra.getUnitatGestora().getIdUnitat());
        Unitat unitatDestinataria = (infra.getUnitatDestinataria() != null)
                ? getUnitatById(infra.getUnitatDestinataria().getIdUnitat()) : null;
        SistemaOperatiu sistemaOperatiu = (infra.getSistemaOperatiu() != null)
                ? getSistemaOperatiuById(infra.getSistemaOperatiu().getIdSistemaOperatiu()) : null;
        UsuariInfraestructura usuariInfraestructura = (infra.getUsuariInfraestructura() != null)
                ? getUsuariInfraestructura(infra.getUsuariInfraestructura().getIdUsuariInfraestructura()) : null;

        infra.setMarca(marca);
        infra.setTipusInfraestructura(tipusInfraestructura);
        infra.setEstat(estat);
        infra.setUnitat(unitat);
        infra.setEdifici(edifici);
        infra.setEstatValidacio(estatValidacio);
        infra.setUnitatGestora(unitatGestora);
        infra.setUnitatDestinataria(unitatDestinataria);
        infra.setSistemaOperatiu(sistemaOperatiu);
        infra.setUsuariInfraestructura(usuariInfraestructura);
    }

    @Override
    public Infraestructura altaInfraestructura(Infraestructura infraestructura) {
        HttpEntity<Infraestructura> req = preparaRequest(infraestructura);

        ResponseEntity<Response<Infraestructura>> rp = restTemplate.exchange(baseUri + "/infraestructura",
                HttpMethod.POST, req, new ParameterizedTypeReference<Response<Infraestructura>>() {
                });

        Response<Infraestructura> response = rp.getBody();
        if (response.isSuccess()) {
            return response.getData();
        }

        throw new EquipsTicClientException(response, "Error en crear la infraestructura: " + response.getMessage());
    }

    /**
     * Mètode auxiliar per crear una petició HTTP.
     * <p>
     * La petició es crearà amb els headers (Accept, Content-Type) i el body
     * adients.
     * 
     * @param infraestructura
     *            la infraestructura que s'afegirà al body en format JSON. No
     *            pot ser {@code null}.
     * @return
     */
    private HttpEntity<Infraestructura> preparaRequest(Infraestructura infraestructura) {
        if (infraestructura == null) {
            throw new IllegalArgumentException("La infraestructura no pot ser null");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(infraestructura, headers);
    }

    @Override
    public void baixaInfraestructura(long id) {
        /*
         * Fem servir 'Object' com a tipus parametritzat perquè en el DELETE
         * l'objecte inclós a la Response és null i no ens importa el seu tipus.
         */
        ResponseEntity<Response<Object>> rp = restTemplate.exchange(baseUri + "/infraestructura/{id}",
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Response<Object>>() {
                }, id);
        Response<Object> response = rp.getBody();
        if (!response.isSuccess()) {
            throw new EquipsTicClientException(response,
                    "Error en esborrar la infraestructura: " + response.getMessage());
        }
    }

    @Override
    public Infraestructura modificaInfraestructura(Infraestructura infraestructura) {
        HttpEntity<Infraestructura> req = preparaRequest(infraestructura);

        ResponseEntity<Response<Infraestructura>> rp = restTemplate.exchange(baseUri + "/infraestructura/{id}",
                HttpMethod.PUT, req, new ParameterizedTypeReference<Response<Infraestructura>>() {
                }, infraestructura.getIdentificador());

        Response<Infraestructura> response = rp.getBody();
        if (response.isSuccess()) {
            return response.getData();
        }
        throw new EquipsTicClientException(response, "Error en modificar la infraestructura: " + response.getMessage());
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatius() {
        List<SistemaOperatiu> result = get("/sistemaOperatiu",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                });
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria) {
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/categoria/{idCategoria}",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                }, idCategoria);
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi) {
        if (StringUtils.isBlank(codi)) {
            throw new IllegalArgumentException("parameter 'codi' can not be blank");
        }
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/codi/{codi}",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                }, codi);
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatiusByNom(String nom) {
        if (StringUtils.isBlank(nom)) {
            throw new IllegalArgumentException("parameter 'nom' can not be blank");
        }
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/nom/{nom}",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                }, nom);
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    public SistemaOperatiu getSistemaOperatiuById(long idSistemaOperatiu) {
        return get("/sistemaOperatiu/{id}", new ParameterizedTypeReference<Response<SistemaOperatiu>>() {
        }, idSistemaOperatiu);
    }

    /**
     * Mètode auxiliar que encapsula crides GET a la API, via
     * {@link RestTemplate}.
     */
    private <T> T get(String url, ParameterizedTypeReference<Response<T>> typeReference, Object... urlParams) {
        String uri = baseUri + url;
        ResponseEntity<Response<T>> entity = restTemplate.exchange(uri, HttpMethod.GET, null, typeReference, urlParams);

        Response<T> response = entity.getBody();

        if (StringUtils.containsIgnoreCase(response.getMessage(), "no existeix")) {
            return null;
        }

        if (response == null || !response.isSuccess()) {
            String errorMsg = String.format("Error en obtenir el recurs: [urlParams: %s, response: %s]",
                    Arrays.toString(urlParams), Objects.toString(response));
            throw new EquipsTicClientException(response, errorMsg);
        }
        return (response != null) ? response.getData() : null;
    }

    /**
     * Ordena la llista donada (<em>null-safe</em>).
     * 
     * @param list
     *            la llista a ordenar; pot ser {@code null}.
     * @return la llista ordenada, o bé una llista buida si
     *         {@code list == null}.
     */
    private <T extends Comparable<T>> List<T> sorted(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        Collections.sort(list);
        return list;
    }

}
