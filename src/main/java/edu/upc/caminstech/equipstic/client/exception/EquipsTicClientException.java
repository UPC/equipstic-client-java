package edu.upc.caminstech.equipstic.client.exception;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientResponseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Excepció del Client que exposa la resposta retornada pel servidor.
 * <p>
 * Es tracta d'una {@link RuntimeException} enriquida amb un nou mètode
 * {@link #getResponse()} per accedir a la resposta retornada pel servidor.
 */
public class EquipsTicClientException extends RuntimeException {

    private static final long serialVersionUID = 5559008561162893859L;

    private final Optional<RestClientResponseException> cause;

    public EquipsTicClientException(RestClientResponseException cause) {
        super(cause);
        this.cause = Optional.of(cause);
    }

    public EquipsTicClientException(String message, RestClientResponseException cause) {
        super(message, cause);
        this.cause = Optional.of(cause);
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
        return cause.map(c -> HttpStatus.valueOf(c.getRawStatusCode()));
    }

    /**
     * Retorna les capçaleres de la resposta del servidor.
     */
    public Optional<HttpHeaders> getHeaders() {
        return cause.map(RestClientResponseException::getResponseHeaders);
    }

    /**
     * Retorna el cos de la resposta del servidor.
     */
    public Optional<String> getBody() {
        return cause.map(RestClientResponseException::getResponseBodyAsString);
    }

    @Override
    public String getMessage() {
        Optional<String> msg = getResponseMessage();

        return String.format("%s: %s - %s", super.getMessage(), getStatus().orElse(null),
                msg.orElse(cause.map(RestClientResponseException::getStatusText).orElse(null)));
    }

    private Optional<String> getResponseMessage() {
        if (!cause.isPresent()) {
            return Optional.ofNullable(null);
        }
        String json = cause.get().getResponseBodyAsString();

        try {
            Map<String, String> map = new ObjectMapper().readValue(json, new TypeReference<Map<String, String>>() {
            });
            return Optional.ofNullable(map.get("message"));
        } catch (IOException e) {
            return Optional.ofNullable(null);
        }
    }

}
