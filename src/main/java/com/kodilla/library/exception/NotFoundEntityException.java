package com.kodilla.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(final String message) {
        super(message);
    }
}
