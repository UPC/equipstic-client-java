package edu.upc.caminstech.equipstic.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.Campus;
import edu.upc.caminstech.equipstic.Categoria;
import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.TipusUs;
import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.Unitat;

/**
 * Aquesta és la classe que implementa el client.
 * <p>
 * S'han seguit les següents convencions:
 * <ul>
 * <li>Les operacions que retornen llistes, mai no retornen {@code null}: si no
 * hi ha elements, retornen una llista buida.</li>
 * <li>Les operacions que retornen objectes, retornen {@code null} si l'objecte
 * no existeix.</li>
 * </ul>
 * <p>
 * Exemple d'utilització:
 * <p>
 * <code>
 * URI uri = URI.create("https://example.com/paht_to_api"); //veure manual SOA <br>
 * EquipsTicClient client = new EquipsTicClient(uri, "soa_user", "soa_password")); <br>
 * List&lt;Campus&gt; campus = client.getCampus();
 * </code>
 */
public class EquipsTicClient {

    private final String baseUri;
    private final RestTemplate restTemplate;

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
     */
    public EquipsTicClient(URI baseUri, String username, String password) {
        this.baseUri = baseUri.toString();
        HttpClient httpClient = prepareHttpClient(baseUri, username, password);
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        fixSupportedMediaTypes(restTemplate);
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

    /**
     * Retorna els àmbits existents.
     */
    public List<Ambit> getAmbits() {
        List<Ambit> result = get("/ambit", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        });
        return (result != null) ? result : new ArrayList<Ambit>();
    }

    /**
     * Cerca d'àmbits per nom.
     */
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        List<Ambit> result = get("/ambit/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        }, nomAmbit);
        return (result != null) ? result : new ArrayList<Ambit>();
    }

    /**
     * Retorna l'àmbit amb l'identificador donat.
     */
    public Ambit getAmbitById(long idAmbit) {
        return get("/ambit/{id}", new ParameterizedTypeReference<Response<Ambit>>() {
        }, idAmbit);
    }

    /**
     * Retorna tots els campus existents.
     */
    public List<Campus> getCampus() {
        List<Campus> result = get("/campus", new ParameterizedTypeReference<Response<List<Campus>>>() {
        });
        return (result != null) ? result : new ArrayList<Campus>();
    }

    /**
     * Retorna el campus amb el codi donat.
     */
    public Campus getCampusByCodi(String codiCampus) {
        return get("/campus/cerca/codi/{codi}", new ParameterizedTypeReference<Response<Campus>>() {
        }, codiCampus);
    }

    /**
     * Retorna el campus amb l'identificador donat.
     */
    public Campus getCampusById(long idCampus) {
        return get("/campus/{id}", new ParameterizedTypeReference<Response<Campus>>() {
        }, idCampus);
    }

    /**
     * Retorna totes les categories existents.
     */
    public List<Categoria> getCategories() {
        List<Categoria> result = get("/categoria", new ParameterizedTypeReference<Response<List<Categoria>>>() {
        });
        return (result != null) ? result : new ArrayList<Categoria>();
    }

    /**
     * Retorna una categoria a partir del seu identificador.
     */
    public Categoria getCategoriaById(long idCategoria) {
        return get("/categoria/{id}", new ParameterizedTypeReference<Response<Categoria>>() {
        }, idCategoria);
    }

    /**
     * Retorna tots els edificis existents.
     */
    public List<Edifici> getEdificis() {
        List<Edifici> result = get("/edifici", new ParameterizedTypeReference<Response<List<Edifici>>>() {
        });
        return (result != null) ? result : new ArrayList<Edifici>();
    }

    /**
     * Retorna un edifici a partir del seu identificador.
     */
    public Edifici getEdificiById(long idEdifici) {
        return get("/edifici/{id}", new ParameterizedTypeReference<Response<Edifici>>() {
        }, idEdifici);
    }

    /**
     * Retorna un edifici a partir d'un codi d'edifici i un codi de campus.
     */
    public Edifici getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus) {
        return get("/edifici/cerca/codi/{codi}/codicampus/{codiCampus}",
                new ParameterizedTypeReference<Response<Edifici>>() {
                }, codiEdifici, codiCampus);
    }

    /**
     * Retorna tots els estats existents.
     */
    public List<Estat> getEstats() {
        List<Estat> result = get("/estat", new ParameterizedTypeReference<Response<List<Estat>>>() {
        });
        return (result != null) ? result : new ArrayList<Estat>();
    }

    /**
     * Retorna un estat a partir del seu codi.
     */
    public Estat getEstatByCodi(String codiEstat) {
        return get("/estat/cerca/codi/{codi}", new ParameterizedTypeReference<Response<Estat>>() {
        }, codiEstat);
    }

    /**
     * Cerca d'estats a partir d'un nom.
     */
    public List<Estat> getEstatsByNom(String nomEstat) {
        List<Estat> result = get("/estat/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Estat>>>() {
        }, nomEstat);
        return (result != null) ? result : new ArrayList<Estat>();
    }

    /**
     * Retorna un estat a partir del seu identificador.
     */
    public Estat getEstatById(long idEstat) {
        return get("/estat/{id}", new ParameterizedTypeReference<Response<Estat>>() {
        }, idEstat);
    }

    /**
     * Retorna totes les marques existents.
     */
    public List<Marca> getMarques() {
        List<Marca> result = get("/marca", new ParameterizedTypeReference<Response<List<Marca>>>() {
        });
        return (result != null) ? result : new ArrayList<Marca>();
    }

    /**
     * Cerca de marques pel nom.
     */
    public List<Marca> getMarquesByNom(String nom) {
        List<Marca> result = get("/marca/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Marca>>>() {
        }, nom);
        return (result != null) ? result : new ArrayList<Marca>();
    }

    /**
     * Retorna una marca a partir del seu identificador.
     */
    public Marca getMarcaById(long idMarca) {
        return get("/marca/{id}", new ParameterizedTypeReference<Response<Marca>>() {
        }, idMarca);
    }

    /**
     * Retorna tots els tipus d'ús existents.
     */
    public List<TipusUs> getTipusUs() {
        List<TipusUs> result = get("/tipusUs", new ParameterizedTypeReference<Response<List<TipusUs>>>() {
        });
        return (result != null) ? result : new ArrayList<TipusUs>();
    }

    /**
     * Cerca tipus d'ús a partir d'una unitat.
     */
    public List<TipusUs> getTipusUsByUnitat(long idUnitat) {
        List<TipusUs> result = get("/tipusUs/cerca/unitat/{idUnitat}",
                new ParameterizedTypeReference<Response<List<TipusUs>>>() {
                }, idUnitat);
        return (result != null) ? result : new ArrayList<TipusUs>();
    }

    /**
     * Retorna un tipus d'ús a partir del seu identificador.
     */
    public TipusUs getTipusUsById(long idTipusUs) {
        return get("/tipusUs/{idTipusUs}", new ParameterizedTypeReference<Response<TipusUs>>() {
        }, idTipusUs);
    }

    /**
     * Retorna tots els tipus d'infraestructura existents.
     */
    public List<TipusInfraestructura> getTipusInfraestructura() {
        List<TipusInfraestructura> result = get("/tipusInfraestructura",
                new ParameterizedTypeReference<Response<List<TipusInfraestructura>>>() {
                });
        return (result != null) ? result : new ArrayList<TipusInfraestructura>();
    }

    /**
     * Cerca de tipus d'infraestructura per categoria.
     */
    public List<TipusInfraestructura> getTipusInfraestructuraBycategoria(long idCategoria) {
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/categoria/{idCategoria}",
                new ParameterizedTypeReference<Response<List<TipusInfraestructura>>>() {
                }, idCategoria);
        return (result != null) ? result : new ArrayList<TipusInfraestructura>();
    }

    /**
     * Retorna un tipus d'infraestructura a partir del seu codi.
     */
    public TipusInfraestructura getTipusInfraestructuraBycodi(String codi) {
        return get("/tipusInfraestructura/cerca/codi/{codi}",
                new ParameterizedTypeReference<Response<TipusInfraestructura>>() {
                }, codi);
    }

    /**
     * Cerca de tipus d'infraestructura a partir del seu nom.
     */
    public List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom) {
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/nom/{nom}",
                new ParameterizedTypeReference<Response<List<TipusInfraestructura>>>() {
                }, nom);
        return (result != null) ? result : new ArrayList<TipusInfraestructura>();
    }

    /**
     * Retorna un tipus d'infraestructura a partir del seu identificador.
     */
    public TipusInfraestructura getTipusInfraestructuraById(long idTipus) {
        return get("/tipusInfraestructura/{id}", new ParameterizedTypeReference<Response<TipusInfraestructura>>() {
        }, idTipus);
    }

    /**
     * Retorna tots els tipus de xarxa existents.
     */
    public List<TipusXarxa> getTipusXarxa() {
        List<TipusXarxa> result = get("/tipusXarxa", new ParameterizedTypeReference<Response<List<TipusXarxa>>>() {
        });
        return (result != null) ? result : new ArrayList<TipusXarxa>();
    }

    /**
     * Retorna un tipus de xarxa a partir del seu identificador.
     */
    public TipusXarxa getTipusXarxaById(long idTipusXarxa) {
        return get("/tipusXarxa/{id}", new ParameterizedTypeReference<Response<TipusXarxa>>() {
        }, idTipusXarxa);
    }

    /**
     * Retorna totes les unitats existents.
     */
    public List<Unitat> getUnitats() {
        List<Unitat> result = get("/unitat", new ParameterizedTypeReference<Response<List<Unitat>>>() {
        });
        return (result != null) ? result : new ArrayList<Unitat>();
    }

    /**
     * Retorna una unitat a partir de l'identificador.
     * <p>
     * No s'ha de confondre amb el mètode {@link #getUnitatById(long)}. Aquest
     * mètode cerca a partir de l'atribut "identificador" de la classe
     * {@link Unitat}.
     *
     * @see {@link Unitat#getIdentificador()}
     */
    public Unitat getUnitatByIdentificador(String identificador) {
        return get("/unitat/cerca/identificador/{identificador}", new ParameterizedTypeReference<Response<Unitat>>() {
        }, identificador);
    }

    /**
     * Cerca d'unitats a partir del nom.
     */
    public List<Unitat> getUnitatsByNom(String nom) {
        List<Unitat> result = get("/unitat/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Unitat>>>() {
        }, nom);
        return (result != null) ? result : new ArrayList<Unitat>();
    }

    /**
     * Cerca d'unitats a partir del nom.
     */
    public List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat) {
        List<Unitat> result = get("/unitat/cerca/nom/{nom}/identificador/{identificador}/codi/{codi}",
                new ParameterizedTypeReference<Response<List<Unitat>>>() {
                }, nom, identificador, codiUnitat);
        return (result != null) ? result : new ArrayList<Unitat>();
    }

    public Unitat getUnitatById(long idUnitat) {
        return get("/unitat/{id}", new ParameterizedTypeReference<Response<Unitat>>() {
        }, idUnitat);
    }

    public Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn) {
        return get("/infraestructura/cerca/marca/{idMarca}/sn/{sn}",
                new ParameterizedTypeReference<Response<Infraestructura>>() {
                }, idMarca, sn);
    }

    public Infraestructura getInfraestructuraById(long id) {
        return get("/infraestructura/{id}", new ParameterizedTypeReference<Response<Infraestructura>>() {
        }, id);

    }

    /**
     * Mètode auxiliar que encapsula crides GET a la API, via
     * {@link RestTemplate}.
     */
    private <T> T get(String url, ParameterizedTypeReference<Response<T>> typeReference, Object... urlParams) {
        String uri = baseUri + url;
        try {
            ResponseEntity<Response<T>> entity = restTemplate.exchange(uri, HttpMethod.GET, null, typeReference,
                    urlParams);

            Response<T> response = entity.getBody();
            RecursNoTrobatException.throwIf(isResourceNotFound(response), response.getMessage());
            return (response != null) ? response.getData() : null;

        } catch (HttpServerErrorException e) {
            if (e.getStatusCode().is5xxServerError()) {
                String msg = String.format("El servidor SOA ha retornat l'error '%s %s'", e.getStatusCode(),
                        e.getStatusText());
                throw new RuntimeException(msg, e);
            } else {
                throw new RuntimeException("Error en fer la petició al servidor", e);
            }
        }
    }

    /**
     * Comprova si la {@link Response} està indicant que no s'ha trobat el
     * recurs. Això és un <em>workaround</em> ja que la API d'EquipsTIC no
     * retorna 404 en cas que el recurs no existeixi (retorna un 200, i indica
     * l'error al estat i al missatge, dins el body).
     */
    private <T> boolean isResourceNotFound(Response<T> response) {
        return (response != null) && "fail".equals(response.getStatus())
                && StringUtils.containsIgnoreCase(response.getMessage(), "no existeix");
    }
}
