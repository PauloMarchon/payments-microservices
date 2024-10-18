package com.payments.customer_service.application.core.domain.v1;

import com.payments.customer_service.application.core.valueobjects.v1.Address;
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

    public static ReadAddress from(Address address) {
        if (address == null)
            throw new NullPointerException("address is null");

        return new ReadAddress(
                address.getZipCode(),
                address.getStreet(),
                address.getDistrict(),
                address.getNumber(),
                address.getComplement(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }
}
