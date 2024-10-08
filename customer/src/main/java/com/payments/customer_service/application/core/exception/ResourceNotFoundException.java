package com.payments.customer_service.application.core.exception;

public class ResourceNotFoundException extends DomainException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
