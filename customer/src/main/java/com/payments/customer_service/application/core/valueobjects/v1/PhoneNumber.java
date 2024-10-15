package com.payments.customer_service.application.core.valueobjects.v1;

public class PhoneNumber {
    private final String number;

    public PhoneNumber(String number) {
        if (!isValid(number))
            throw new IllegalArgumentException("Invalid phone number");

        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isValid(String number) {
        return true;
    }
}
