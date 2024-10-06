package com.payments.customer_service.application.core.usecases.v1.address;

import com.payments.customer_service.application.core.domain.v1.Address;
import com.payments.customer_service.application.core.usecases.repositories.v1.AddressRepository;
import lombok.Builder;

public interface CreateAddressUseCase {

    Response execute(Request requestData);

    @Builder
    record Request(
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

    record Response(
            String ref
    ) {

    }

    class DefaultCreateAddressUseCase implements CreateAddressUseCase {
        private final AddressRepository addressRepository;

        public DefaultCreateAddressUseCase(AddressRepository addressRepository) {
            this.addressRepository = addressRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.zipCode == null)
                throw new IllegalArgumentException("zipCode cannot be null");

            if (requestData.street == null)
                throw new IllegalArgumentException("street cannot be null");

            if (requestData.district == null)
                throw new IllegalArgumentException("district cannot be null");

            if (requestData.number == null)
                throw new IllegalArgumentException("number cannot be null");

            if (requestData.city == null)
                throw new IllegalArgumentException("city cannot be null");

            if (requestData.state == null)
                throw new IllegalArgumentException("state cannot be null");

            if (requestData.country == null)
                throw new IllegalArgumentException("country cannot be null");

            Address dbAddress = Address.builder()
                    .zipCode(requestData.zipCode)
                    .street(requestData.street)
                    .district(requestData.district)
                    .number(requestData.number)
                    .complement(requestData.complement)
                    .city(requestData.city)
                    .state(requestData.state)
                    .country(requestData.country)
                    .build();

            return new CreateAddressUseCase.Response(
                    addressRepository.save(dbAddress)
            );
        }
    }
}