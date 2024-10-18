package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.domain.v1.ReadCustomer;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface GetCustomerByIdUseCase {

    Response execute(Request requestData);

    record Request(
            UUID customerRef
    ) {

    }

    record Response(
            ReadCustomer customer
    ) {

    }

    class DefaultGetCustomerByIdUseCase implements GetCustomerByIdUseCase {
        private final CustomerRepository customerRepository;

        public DefaultGetCustomerByIdUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.customerRef() == null)
                throw new IllegalArgumentException("ref must not be null");

           Customer customer = customerRepository.findById(requestData.customerRef())
                            .orElseThrow(() -> new IllegalArgumentException("customer with id not found"));

            return new GetCustomerByIdUseCase.Response(
                    ReadCustomer.from(customer)
            );
        }
    }
}