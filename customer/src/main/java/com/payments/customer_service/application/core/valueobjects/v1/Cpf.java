package com.payments.customer_service.application.core.valueobjects.v1;

public class Cpf implements IdentificationNumber{
    private final String cpf;

    public Cpf(String cpf) {
        if (!isValid(cpf))
            throw new IllegalArgumentException("Invalid CPF");

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
