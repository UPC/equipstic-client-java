package edu.upc.caminstech.equipstic.client;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Excepció del Client que exposa la resposta retornada pel servidor.
 * <p>
 * Es tracta d'una {@link RuntimeException} enriquida amb un nou mètode
 * {@link #getResponse()} per accedir a la resposta retornada pel servidor.
 */
public class EquipsTicClientException extends RuntimeException {

    private static final long serialVersionUID = 5559008561162893859L;

    private final Response<?> response;
    private final HttpStatus status;
    private final HttpHeaders headers;

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response) {
        super();
        Assert.notNull(entity, "Entity can not be null");
        this.response = response;
        this.status = entity.getStatusCode();
        this.headers = entity.getHeaders();
    }

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response, String message) {
        super(message);
        Assert.notNull(entity, "Entity can not be null");
        this.response = response;
        this.status = entity.getStatusCode();
        this.headers = entity.getHeaders();
    }

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response, Throwable cause) {
        super(cause);
        Assert.notNull(entity, "Entity can not be null");
        this.response = response;
        this.status = entity.getStatusCode();
        this.headers = entity.getHeaders();
    }

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response, String message, Throwable cause) {
        super(message, cause);
        Assert.notNull(entity, "Entity can not be null");
        this.response = response;
        this.status = entity.getStatusCode();
        this.headers = entity.getHeaders();
    }

    public EquipsTicClientException(HttpClientErrorException e) {
        super(e);
        this.response = null;
        this.status = e.getStatusCode();
        this.headers = e.getResponseHeaders();
    }

    /**
     * Retorna la resposta del servidor.
     */
    public Optional<Response<?>> getResponse() {
        return Optional.ofNullable(response);
    }

    /**
     * Retorna l'status HTTP de la resposta del servidor.
     * 
     * @return
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Retorna els headers de la resposta del servidor.
     */
    public HttpHeaders getHeaders() {
        return headers;
    }

}
