package com.payments.customer_service.application.core.domain.v1;

import lombok.Builder;

@Builder
public record ReadCompany(
        String ref,
        String identificationNumber,
        String companyName,
        String fantasyName,
        String openingDate,
        String email,
        String phoneNumber,
        ReadAddress address,
        ReadCustomer customer
) {

}
