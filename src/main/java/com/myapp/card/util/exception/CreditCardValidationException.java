package com.myapp.card.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreditCardValidationException extends RuntimeException {
    public CreditCardValidationException() {
        super();
    }

    public CreditCardValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditCardValidationException(String message) {
        super(message);
    }

    public CreditCardValidationException(Throwable cause) {
        super(cause);
    }
}
