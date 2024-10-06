package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface DeleteCustomerUseCase {

    Response execute(Request requestData);

    record Request(
            String ref
    ) {

    }

    record Response(

    ) {

    }

    class DefaultDeleteCustomerUseCase implements DeleteCustomerUseCase {
        private final CustomerRepository customerRepository;

        public DefaultDeleteCustomerUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.ref == null) {
                throw new IllegalArgumentException("ref is null");
            } else {
                try {
                    UUID.fromString(requestData.ref);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("ref is invalid");
                }
            }
            customerRepository.delete(UUID.fromString(requestData.ref));

            return new DeleteCustomerUseCase.Response();
        }
    }
}
