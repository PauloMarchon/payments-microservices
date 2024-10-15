package com.payments.customer_service.application.core.domain.v1;

import lombok.Builder;

@Builder
public record ReadCustomer(
        String ref,
        String fullName,
        String identificationNumber,
        String dateOfBirth,
        String email,
        String phoneNumber,
        String companyName
) {

}
