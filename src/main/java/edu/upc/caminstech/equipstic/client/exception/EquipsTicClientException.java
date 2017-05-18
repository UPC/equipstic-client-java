package edu.upc.caminstech.equipstic.client.exception;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientResponseException;

/**
 * Excepció del Client que exposa la resposta retornada pel servidor.
 * <p>
 * Es tracta d'una {@link RuntimeException} enriquida amb un nou mètode
 * {@link #getResponse()} per accedir a la resposta retornada pel servidor.
 */
public class EquipsTicClientException extends RuntimeException {

    private static final long serialVersionUID = 5559008561162893859L;

    private final RestClientResponseException cause;

    public EquipsTicClientException(RestClientResponseException cause) {
        super(cause);
        this.cause = cause;
    }

    public EquipsTicClientException(String message, RestClientResponseException cause) {
        super(message, cause);
        this.cause = cause;
    }

    public EquipsTicClientException(ResponseEntity<?> entity) {
        super();
        Assert.notNull(entity, "Entity can not be null");
        this.cause = null;
    }

    public EquipsTicClientException(ResponseEntity<?> entity, String message) {
        super(message);
        Assert.notNull(entity, "Entity can not be null");
        this.cause = null;
    }

    /**
     * Retorna el codi d'estat HTTP de la resposta del servidor.
     */
    public Optional<HttpStatus> getStatus() {
        return Optional.ofNullable(cause).map(c -> HttpStatus.valueOf(c.getRawStatusCode()));
    }

    /**
     * Retorna les capçaleres de la resposta del servidor.
     */
    public Optional<HttpHeaders> getHeaders() {
        return Optional.ofNullable(cause).map(RestClientResponseException::getResponseHeaders);
    }

    /**
     * Retorna el cos de la resposta del servidor.
     */
    public Optional<String> getBody() {
        return Optional.ofNullable(cause).map(RestClientResponseException::getResponseBodyAsString);
    }

    @Override
    public String getMessage() {
        return String.format("%s: %s - %s", super.getMessage(), getStatus().orElse(null), cause.getStatusText());
    }
}
