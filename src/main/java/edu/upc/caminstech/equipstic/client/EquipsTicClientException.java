package edu.upc.caminstech.equipstic.client;

/**
 * Excepció del Client que exposa la resposta retornada pel servidor.
 * <p>
 * Es tracta d'una {@link RuntimeException} enriquida amb un nou mètode
 * {@link #getResponse()} per accedir a la resposta generada pel servidor .
 */
public class EquipsTicClientException extends RuntimeException {

    private static final long serialVersionUID = 5559008561162893859L;

    private final Response<?> response;

    public EquipsTicClientException(Response<?> response) {
        super();
        this.response = response;
    }

    public EquipsTicClientException(Response<?> response, String message) {
        super(message);
        this.response = response;
    }

    public EquipsTicClientException(Response<?> response, Throwable cause) {
        super(cause);
        this.response = response;
    }

    public EquipsTicClientException(Response<?> response, String message, Throwable cause) {
        super(message, cause);
        this.response = response;
    }

    /**
     * Retorna la resposta del servidor.
     */
    public Response<?> getResponse() {
        return response;
    }

}
