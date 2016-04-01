package edu.upc.caminstech.equipstic.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.Campus;

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
     *            Es pot fer servir tant la URL de proves com la de producció.
     * @param username
     *            el nostre usuari al bus SOA (ha de tenir accés a la API).
     * @param password
     *            la nostra contrasenya al bus SOA.
     */
    public EquipsTicClient(URI baseUri, String username, String password) {
        this.baseUri = baseUri.toString();

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials(username, password));
        HttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));

        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
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
        List<Ambit> ambits = get("/ambit", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        });
        return (ambits != null) ? ambits : new ArrayList<Ambit>();
    }

    /**
     * Cerca d'àmbits per nom.
     */
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        List<Ambit> ambits = get("/ambit/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        }, nomAmbit);
        return (ambits != null) ? ambits : new ArrayList<Ambit>();
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
        List<Campus> campus = get("/campus", new ParameterizedTypeReference<Response<List<Campus>>>() {
        });
        return (campus != null) ? campus : new ArrayList<Campus>();
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
     * Mètode auxiliar que encapsula crides GET a la API, via
     * {@link RestTemplate}.
     */
    private <T> T get(String url, ParameterizedTypeReference<Response<T>> typeReference, Object... x) {
        Response<T> response = restTemplate.exchange(baseUri + url, HttpMethod.GET, null, typeReference, x).getBody();
        return (response != null) ? response.getData() : null;
    }
}
