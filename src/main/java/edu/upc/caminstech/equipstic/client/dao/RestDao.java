package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;
import edu.upc.caminstech.equipstic.client.exception.EquipsTicClientException;
import edu.upc.caminstech.equipstic.client.exception.UnauthorizedException;

/**
 * Classe d'ús intern de la llibreria.
 */
public class RestDao {

    protected final Logger logger = LoggerFactory.getLogger(RestDao.class);

    private final URI baseUri;
    private final RestTemplate restTemplate;

    public RestDao(EquipsTicClientConfiguration config) {
        this.baseUri = config.getBaseUri();
        this.restTemplate = config.getRestTemplate();
    }

    public URI getBaseUri() {
        return this.baseUri;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    /**
     * Mètode auxiliar que encapsula crides GET a la API, via
     * {@link RestTemplate}.
     */
    public <T> T get(String url, ParameterizedTypeReference<Response<T>> typeReference, Object... urlParams) {
        try {
            ResponseEntity<Response<T>> entity = restTemplate.exchange(baseUri + url, HttpMethod.GET, null,
                    typeReference, urlParams);

            Response<T> body = entity.getBody();

            if (body == null) {
                throw new EquipsTicClientException(entity,
                        "Error en la crida GET: s'ha obtingut una resposta amb body null");
            } else {
                return body.getData();
            }
        } catch (RestClientResponseException e) {
            String msg = String.format("Error en obtenir el recurs [%s]", getResourcePath(url, urlParams));
            if (e.getRawStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
                throw new UnauthorizedException(msg, e);
            }
            throw new EquipsTicClientException(msg, e);
        }
    }

    /**
     * Mètode auxiliar que encapsula crides DELETE a la API, via
     * {@link RestTemplate}.
     */
    public <T> void delete(String url, ParameterizedTypeReference<Response<T>> typeReference, Object... urlParams) {
        try {
            restTemplate.exchange(baseUri + url, HttpMethod.DELETE, null, typeReference, urlParams);
        } catch (RestClientResponseException e) {
            String msg = String.format("Error en obtenir el recurs [%s]", getResourcePath(url, urlParams));
            throw new EquipsTicClientException(msg, e);
        }
    }

    private String getResourcePath(String url, Object... urlParams) {
        return UriComponentsBuilder.fromPath(url).buildAndExpand(urlParams).toUri().toString();
    }

}
