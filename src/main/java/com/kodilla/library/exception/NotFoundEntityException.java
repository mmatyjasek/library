package com.kodilla.library.exception;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(final String message) {
        super(message);
    }
}