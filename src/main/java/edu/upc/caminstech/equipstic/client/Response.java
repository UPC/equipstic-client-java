package edu.upc.caminstech.equipstic.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.upc.caminstech.equipstic.client.exception.EquipsTicClientException;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa una resposta del servidor d'EquipsTIC.
 * <p>
 * Els usuaris normalment no haurien d'accedir a aquesta classe, però pot ser
 * útil per depurar errors (podeu obtenir una referència via
 * {@link EquipsTicClientException#getResponse()}).
 */
@JsonInclude(Include.NON_NULL)
public class Response<T> {

    public static final String STATUS_SUCCESS = "success";

    public static final String STATUS_FAILURE = "fail";

    private final String status;
    private final String success;
    private final String message;
    private final T data;
    private final Integer totalElements;

    @JsonCreator
    public Response(@JsonProperty("status") String status, @JsonProperty("success") String success,
            @JsonProperty("message") String message, @JsonProperty(value = "data", required = false) T data,
            @JsonProperty(value = "totalElements", required = false) Integer totalElements) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
        this.totalElements = totalElements;
    }

    public String getStatus() {
        return status;
    }

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public boolean isSuccess() {
        return STATUS_SUCCESS.equals(status) || STATUS_SUCCESS.equals(success);
    }

    @Override
    public String toString() {
        return String.format("[Response<%s> status: %s, success: %s, message: %s, data: %s]",
                data != null ? data.getClass().getSimpleName() : "", status, success, message,
                data != null ? data.toString() : "");
    }

}
