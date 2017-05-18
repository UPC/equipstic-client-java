package edu.upc.caminstech.equipstic.client.dao;

import static java.util.stream.Collectors.toList;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;
import edu.upc.caminstech.equipstic.client.exception.EquipsTicClientException;

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
            return entity.getBody().getData();
        } catch (RestClientResponseException e) {
            String msg = String.format("Error en obtenir el recurs [%s]", getResourcePath(url, urlParams));
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

    /**
     * Mètode auxiliar <em>null-safe</em> per ordenar llistes genèriques.
     * 
     * @param list
     *            la llista a ordenar (pot ser {@code null}).
     * @return la llista ordenada (pot estar buida).
     */
    public static <T extends Comparable<T>> List<T> sorted(List<T> list) {
        /**
         * TODO: Aquest mètode s'hauria de moure a una altra classe, ja que és
         * massa genèric.
         */
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().sorted().collect(toList());
    }

    private String getResourcePath(String url, Object... urlParams) {
        return UriComponentsBuilder.fromPath(url).buildAndExpand(urlParams).toUri().toString();
    }

}
