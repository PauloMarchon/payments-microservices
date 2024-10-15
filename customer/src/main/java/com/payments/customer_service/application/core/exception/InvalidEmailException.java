package com.payments.customer_service.application.core.exception;

public class InvalidEmailException extends DomainException{
    public InvalidEmailException(String message) {
        super(message);
    }
}
