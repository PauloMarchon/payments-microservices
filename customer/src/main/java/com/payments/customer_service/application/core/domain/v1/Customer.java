package com.payments.customer_service.application.core.domain.v1;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Customer(
        UUID id,
        String fullName,
        IdentificationNumber identificationNumber,
        LocalDate dateOfBirth,
        PhoneNumber phoneNumber,
        UUID addressRef
) {

}
