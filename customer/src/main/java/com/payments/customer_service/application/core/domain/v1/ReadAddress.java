package com.payments.customer_service.application.core.domain.v1;

import lombok.Builder;

@Builder
public record ReadAddress(
        String zipCode,
        String street,
        String district,
        Integer number,
        String complement,
        String city,
        String state,
        String country
) {
}
