package com.payments.customer_service.application.core.usecases.v1.address;

import com.payments.customer_service.application.core.usecases.repositories.v1.AddressRepository;

import java.util.UUID;

public interface DeleteAddressUseCase {

    Response execute(Request requestData);

    record Request(
            String ref
    ) {

    }

    record Response(

    ) {

    }

    class DefaultDeleteAddressUseCase implements DeleteAddressUseCase {
        private final AddressRepository addressRepository;

        public DefaultDeleteAddressUseCase(AddressRepository addressRepository) {
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
            addressRepository.delete(UUID.fromString(requestData.ref));

            return new DeleteAddressUseCase.Response();
        }
    }
}
