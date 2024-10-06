package com.payments.customer_service.application.core.usecases.v1.address;

import com.payments.customer_service.application.core.domain.v1.Address;
import com.payments.customer_service.application.core.domain.v1.ReadAddress;
import com.payments.customer_service.application.core.usecases.repositories.v1.AddressRepository;

import java.util.UUID;

public interface GetAddressUseCase {

    Response execute(Request requestData);

    record Request(
            String ref
    ) {

    }

    record Response(
        ReadAddress readAddress
    ) {

    }

    class DefaultGetAddressUseCase implements GetAddressUseCase {
        private final AddressRepository addressRepository;

        public DefaultGetAddressUseCase(AddressRepository addressRepository) {
            this.addressRepository = addressRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.ref == null) {
                throw new IllegalArgumentException("ref is null");
            } else {
                try {
                    UUID.fromString(requestData.ref);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ref is not a valid UUID");
                }
            }

            Address dbAddress = addressRepository.get(UUID.fromString(requestData.ref))
                    .orElseThrow(() -> new IllegalArgumentException(""));

            return new GetAddressUseCase.Response(
                    ReadAddress.builder()
                            .zipCode(dbAddress.zipCode())
                            .street(dbAddress.street())
                            .district(dbAddress.district())
                            .number(dbAddress.number())
                            .complement(dbAddress.complement())
                            .city(dbAddress.city())
                            .state(dbAddress.state())
                            .country(dbAddress.country())
                            .build()
            );
        }
    }
}
