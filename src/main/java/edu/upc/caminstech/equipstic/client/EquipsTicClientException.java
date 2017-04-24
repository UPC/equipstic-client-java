package edu.upc.caminstech.equipstic.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Excepció del Client que exposa la resposta retornada pel servidor.
 * <p>
 * Es tracta d'una {@link RuntimeException} enriquida amb un nou mètode
 * {@link #getResponse()} per accedir a la resposta retornada pel servidor.
 */
public class EquipsTicClientException extends RuntimeException {

    private static final long serialVersionUID = 5559008561162893859L;

    private final ResponseEntity<?> entity;
    private final Response<?> response;

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response) {
        super();
        this.entity = entity;
        this.response = response;
    }

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response, String message) {
        super(message);
        this.entity = entity;
        this.response = response;
    }

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response, Throwable cause) {
        super(cause);
        this.entity = entity;
        this.response = response;
    }

    public EquipsTicClientException(ResponseEntity<?> entity, Response<?> response, String message, Throwable cause) {
        super(message, cause);
        this.entity = entity;
        this.response = response;
    }

    /**
     * Retorna la resposta del servidor.
     */
    public Response<?> getResponse() {
        return response;
    }

    public HttpStatus getStatusCode() {
        return entity != null ? entity.getStatusCode() : null;
    }

    public HttpHeaders getHeaders() {
        return entity != null ? entity.getHeaders() : null;
    }

}
