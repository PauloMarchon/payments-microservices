package com.payments.customer_service.application.core.domain.v1;

public interface IdentificationNumber {

    String getNumber();

    boolean isValid(String number);
}
