package com.payments.customer_service.application.core.domain.v1;

public class Cnpj implements IdentificationNumber {
    private final String cnpj;

    public Cnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getNumber() {
        return cnpj;
    }

    @Override
    public boolean isValid(String number) {
        return false;
    }
}
