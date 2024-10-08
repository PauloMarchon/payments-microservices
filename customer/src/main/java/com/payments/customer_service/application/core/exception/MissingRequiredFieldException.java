package com.payments.customer_service.application.core.exception;

public class MissingRequiredFieldException extends DomainException {

    public MissingRequiredFieldException(String missingFieldName) {
        super("Missing required field: " + missingFieldName);
    }
}