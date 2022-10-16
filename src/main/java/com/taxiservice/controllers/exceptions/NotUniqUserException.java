package com.taxiservice.controllers.exceptions;

public class NotUniqUserException extends RuntimeException {

    public NotUniqUserException() {
        super();
    }

    public NotUniqUserException(String message) {
        super(message);
    }
}
