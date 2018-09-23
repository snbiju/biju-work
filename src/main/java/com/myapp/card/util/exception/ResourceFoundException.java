package com.myapp.card.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Biju Pillai
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceFoundException extends RuntimeException {
    public ResourceFoundException() {
        super();
    }

    public ResourceFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceFoundException(String message) {
        super(message);
    }

    public ResourceFoundException(Throwable cause) {
        super(cause);
    }
}
