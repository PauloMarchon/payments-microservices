package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface DeleteCustomerUseCase {

    Response execute(Request requestData);

    record Request(
            UUID customerRef
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
            if (requestData.customerRef == null)
                throw new IllegalArgumentException("ref is null");

            customerRepository.delete(requestData.customerRef);

            return new DeleteCustomerUseCase.Response();
        }
    }
}