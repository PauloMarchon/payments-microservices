package com.payments.customer_service.application.core.exception;

public class InvalidIdentificationNumberException extends DomainException{
    public InvalidIdentificationNumberException(String message) {
        super(message);
    }

    public InvalidIdentificationNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
