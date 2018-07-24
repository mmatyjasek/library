package com.kodilla.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There are no available copies of this book")
public class NotAvailableException extends RuntimeException {
    public NotAvailableException(final String message) {
        super(message);
    }
}
