package com.taxiservice.controllers.exceptions;

public class SettleUpDuplicationException extends RuntimeException {

    public SettleUpDuplicationException() {
        super();
    }

    public SettleUpDuplicationException(String message) {
        super(message);
    }
}
