package edu.upc.caminstech.equipstic.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecursNoTrobatException extends RuntimeException {

    private static final long serialVersionUID = 2485747546342391859L;

    public RecursNoTrobatException() {
        super();
    }

    public RecursNoTrobatException(String message) {
        super(message);
    }

    public RecursNoTrobatException(Throwable cause) {
        super(cause);
    }

    public RecursNoTrobatException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecursNoTrobatException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static void throwIf(boolean condition, String message) {
        if (condition) {
            throw new RecursNoTrobatException(message);
        }
    }

}
