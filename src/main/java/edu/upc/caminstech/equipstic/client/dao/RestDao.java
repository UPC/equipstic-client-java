package edu.upc.caminstech.equipstic.client.dao;

import static java.util.stream.Collectors.toList;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.client.EquipsTicClientException;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Aquesta classe és d'ús intern de la llibreria.
 */
public class RestDao {

    private final URI baseUri;
    private final RestTemplate restTemplate;

    public RestDao(URI baseUri, RestTemplate restTemplate) {
        this.baseUri = baseUri;
        this.restTemplate = restTemplate;
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

        ResponseEntity<Response<T>> entity = restTemplate.exchange(baseUri + url, HttpMethod.GET, null, typeReference,
                urlParams);

        Response<T> response = entity.getBody();

        if (response != null && StringUtils.containsIgnoreCase(response.getMessage(), "no existeix")) {
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
    public static <T extends Comparable<T>> List<T> sorted(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().sorted().collect(toList());
    }
}
