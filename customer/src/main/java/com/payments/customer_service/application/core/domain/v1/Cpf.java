package com.payments.customer_service.application.core.domain.v1;

public class Cpf implements IdentificationNumber{
    private final String cpf;

    public Cpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getNumber() {
        return cpf;
    }

    @Override
    public boolean isValid(String number) {
        return false;
    }
}
