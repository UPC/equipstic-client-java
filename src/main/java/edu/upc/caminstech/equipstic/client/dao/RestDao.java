package edu.upc.caminstech.equipstic.client.dao;

import static java.util.stream.Collectors.toList;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.EquipsTicClientException;
import edu.upc.caminstech.equipstic.client.Response;

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
        ResponseEntity<Response<T>> entity = null;

        try {
            entity = restTemplate.exchange(baseUri + url, HttpMethod.GET, null, typeReference, urlParams);
        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
            case NOT_FOUND:
                throw new EquipsTicClientException("No s'ha trobat el recurs", e);
            case UNAUTHORIZED:
                throw new EquipsTicClientException("No teniu privilegis per accedir al recurs", e);
            default:
                throw new EquipsTicClientException("S'ha produït un error", e);
            }
        } catch (HttpServerErrorException e) {
            throw new EquipsTicClientException("El servidor ha generat un error", e);
        }

        return entity.getBody().getData();
    }

    /**
     * Mètode auxiliar que encapsula crides DELETE a la API, via
     * {@link RestTemplate}.
     */
    public <T> void delete(String url, ParameterizedTypeReference<Response<T>> typeReference, Object... urlParams) {
        try {
            restTemplate.exchange(baseUri + url, HttpMethod.DELETE, null, typeReference, urlParams);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new EquipsTicClientException(e);
            }
            throw e;
        }
    }

    /**
     * Mètode auxiliar <em>null-safe</em> per ordenar llistes genèriques.
     * 
     * @param list
     *            la llista a ordenar (pot ser {@code null}).
     * @return la llista ordenada (pot estar buida).
     */
    public static <T extends Comparable<T>> List<T> sorted(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().sorted().collect(toList());
    }
}
