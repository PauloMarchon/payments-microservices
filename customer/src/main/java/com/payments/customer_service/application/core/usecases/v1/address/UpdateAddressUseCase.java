package com.payments.customer_service.application.core.usecases.v1.address;

import com.payments.customer_service.application.core.domain.v1.Address;
import com.payments.customer_service.application.core.usecases.repositories.v1.AddressRepository;

import java.util.UUID;

public interface UpdateAddressUseCase {

    Response execute(Request requestData);

    record Request(
            String ref,
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

    class DefaultUpdateAddressUseCase implements UpdateAddressUseCase {
        private final AddressRepository addressRepository;

        public DefaultUpdateAddressUseCase(AddressRepository addressRepository) {
            this.addressRepository = addressRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.ref == null || requestData.ref.isEmpty()) {
                throw new IllegalArgumentException("'ref' is required");
            } else {
                try {
                    UUID.fromString(requestData.ref);
                } catch (Exception e) {
                    throw new IllegalArgumentException("'ref' is not a valid UUID");
                }
            }

            if (requestData.zipCode == null || requestData.zipCode.isEmpty())
                throw new IllegalArgumentException("'zipCode' is required");

            if (requestData.street == null || requestData.street.isEmpty())
                throw new IllegalArgumentException("'street' is required");

            if (requestData.district == null || requestData.district.isEmpty())
                throw new IllegalArgumentException("'district' is required");

            if (requestData.number == null)
                throw new IllegalArgumentException("'number' is required");

            if (requestData.city == null || requestData.city.isEmpty())
                throw new IllegalArgumentException("'city' is required");

            if (requestData.state == null || requestData.state.isEmpty())
                throw new IllegalArgumentException("'state' is required");

            if (requestData.country == null || requestData.country.isEmpty())
                throw new IllegalArgumentException("'country' is required");

            if (!addressRepository.exists(UUID.fromString(requestData.ref)))
                throw new IllegalArgumentException("'ref' is not a valid UUID");

            Address dbAddress = Address.builder()
                    .id(UUID.fromString(requestData.ref))
                    .zipCode(requestData.zipCode)
                    .street(requestData.street)
                    .district(requestData.district)
                    .number(requestData.number)
                    .complement(requestData.complement)
                    .city(requestData.city)
                    .state(requestData.state)
                    .country(requestData.country)
                    .build();

            return new UpdateAddressUseCase.Response(
                    addressRepository.save(dbAddress)
            );
        }
    }
}
