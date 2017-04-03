package edu.upc.caminstech.equipstic.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * Aquesta classe encapsula la configuració d'un client.
 */
public class EquipsTicClientConfiguration {

    private final URI baseUri;
    private final RestTemplate restTemplate;

    /**
     * Construeix una configuració que es pot utilitzar per instanciar un nou
     * client.
     * 
     * @param baseUri
     *            la URL on el servidor EquipsTIC publica la API REST.
     * @param username
     *            el vostre username del bus SOA.
     * @param password
     *            el vostre password del bus SOA.
     * @throws URISyntaxException
     *             si {@code baseUri} no és una URL vàlida.
     */
    public EquipsTicClientConfiguration(String baseUri, String username, String password) throws URISyntaxException {
        Assert.notNull(baseUri, "l'argument baseUri no pot ser null");
        Assert.notNull(username, "l'argument username no pot ser null");
        Assert.notNull(password, "l'argument password no pot ser null");

        this.baseUri = new URI(baseUri);
        this.restTemplate = EquipsTicRestTemplateBuilder.createRestTemplate(this.baseUri, username, password);
    }

    public URI getBaseUri() {
        return baseUri;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
