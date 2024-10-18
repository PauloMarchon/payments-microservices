package com.payments.customer_service.application.core.domain.v1;

import lombok.Builder;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Builder
public record ReadCustomer(
        String ref,
        String fullName,
        String identificationNumber,
        String dateOfBirth,
        String email,
        String phoneNumber
) {

    public static ReadCustomer from(Customer customer) {
        if (customer == null)
            throw new IllegalArgumentException("Customer cannot be null");

        return new ReadCustomer(
                customer.getId().toString(),
                customer.getFullName(),
                customer.getIdentificationNumber().getNumber(),
                customer.getBirthDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                customer.getEmail().getEmail(),
                customer.getPhoneNumber().getNumber()
        );
    }
}
