package edu.upc.caminstech.equipstic.client;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.TimeZone;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe per instanciar la {@link RestTemplate} que utilitza el client.
 * <p>
 * Aquesta classe és d'ús intern de la llibreria, i no és útil per als usuaris.
 */
public abstract class EquipsTicRestTemplateBuilder {

    /**
     * La TimeZone que fa servir el servidor d'EquipsTIC.
     * <p>
     * Com que la API d'EquipsTIC no inclou el timezone en el format de les
     * dates, la llibreria Jackson (de)serialitza els valors interpretant que
     * les dates estan per defecte en el timezone UTC. Però això no és correcte,
     * perquè sembla que el servidor EquipsTIC retorna les dates en el timezone
     * CET.
     */
    private static final TimeZone EQUIPSTIC_SERVER_TIMEZONE = TimeZone.getTimeZone("Europe/Madrid");

    private EquipsTicRestTemplateBuilder() {
        // constructor privat; classe no instanciable
    }

    public static RestTemplate createRestTemplate(URI baseUri, String username, String password, TimeZone timeZone) {
        HttpClient httpClient = prepareHttpClient(baseUri, username, password);
        return prepareRestTemplate(httpClient, timeZone);
    }

    public static RestTemplate createRestTemplate(URI baseUri, String username, String password) {
        return createRestTemplate(baseUri, username, password, EQUIPSTIC_SERVER_TIMEZONE);
    }

    /**
     * Mètode auxiliar per instanciar un HttpClient a partir de les credencials
     * d'autenticació.
     */
    private static HttpClient prepareHttpClient(URI baseUri, String username, String password) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(baseUri.getHost(), baseUri.getPort());
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        credsProvider.setCredentials(authScope, credentials);
        return HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
    }

    private static RestTemplate prepareRestTemplate(HttpClient httpClient, TimeZone timeZone) {
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
    private static void fixJacksonObjectMapperTimezone(RestTemplate template, TimeZone timeZone) {
        ObjectMapper mapper = getObjectMapper(template);
        if (mapper != null) {
            mapper.setTimeZone(timeZone);
        }
    }

    private static ObjectMapper getObjectMapper(RestTemplate template) {
        MappingJackson2HttpMessageConverter converter = getJacksonMessageConverterIfPresent(template);
        if (converter != null) {
            return converter.getObjectMapper();
        }
        return null;
    }

    /**
     * Reconfigura els Converters de la RestTemplate per tal que acceptin també
     * respostes de tipus "text/plain".
     *
     * Això és necessari perquè la API retorna erròniament un Content-Type amb
     * valor "text/plain" quan hauria de ser "application/json".
     */
    private static void fixSupportedMediaTypes(RestTemplate template) {
        MappingJackson2HttpMessageConverter converter = getJacksonMessageConverterIfPresent(template);
        if (converter != null) {
            converter.setSupportedMediaTypes(Arrays.asList( //
                    new MediaType("application", "json", StandardCharsets.UTF_8),
                    new MediaType("text", "plain", StandardCharsets.UTF_8)));
        }
    }

    private static MappingJackson2HttpMessageConverter getJacksonMessageConverterIfPresent(RestTemplate template) {
        Optional<HttpMessageConverter<?>> converter = template.getMessageConverters().stream()
                .filter(MappingJackson2HttpMessageConverter.class::isInstance).findFirst();
        if (converter.isPresent()) {
            return (MappingJackson2HttpMessageConverter) converter.get();
        }
        return null;
    }
}
