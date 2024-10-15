package com.payments.customer_service.application.core.valueobjects.v1;

public interface IdentificationNumber {

    String getNumber();

    boolean isValid(String number);

    default IdentificationNumber identificationType(String number) {

        return switch (number.length()) {
            case 11 -> new Cpf(number);
            case 14 -> new Cnpj(number);
            default -> throw new NumberFormatException();
        };
    }
}
