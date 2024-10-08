package com.payments.customer_service.application.core.exception;

public class InvalidPhoneNumberException extends DomainException{
    public InvalidPhoneNumberException(String message) {
        super(message);
    }

    public InvalidPhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
