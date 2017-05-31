package edu.upc.caminstech.equipstic.client.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

/**
 * Subclasse de {@link EquipsTicClientException} que indica privilegis
 * insuficients en intentar accedir algun recurs del servidor EquipsTIC.
 */
public class UnauthorizedException extends EquipsTicClientException {

    private static final long serialVersionUID = 5476527177359727700L;

    public UnauthorizedException(RestClientResponseException cause) {
        super(cause);
    }

    public UnauthorizedException(String message, RestClientResponseException cause) {
        super(message, cause);
    }

    public UnauthorizedException(ResponseEntity<?> entity) {
        super(entity);
    }

    public UnauthorizedException(ResponseEntity<?> entity, String message) {
        super(entity, message);
    }

    public static UnauthorizedException of(String msg, EquipsTicClientException e) {
        return new UnauthorizedException(msg, (RestClientResponseException) e.getCause());
    }
}
