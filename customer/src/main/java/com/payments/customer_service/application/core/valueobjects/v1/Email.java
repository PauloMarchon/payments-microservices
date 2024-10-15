package com.payments.customer_service.application.core.valueobjects.v1;

public class Email {
    private final String email;

    public Email(String email) {
        if (!isValid(email))
            throw new IllegalArgumentException("Invalid email");

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public boolean isValid(String email) {
        return true;
    }
}
