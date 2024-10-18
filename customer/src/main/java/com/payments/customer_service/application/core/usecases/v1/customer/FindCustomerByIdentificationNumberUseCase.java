package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.domain.v1.ReadCustomer;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

public interface FindCustomerByIdentificationNumberUseCase {

    Response execute(Request requestData);

    record Request(
        IdentificationNumber identificationNumber
    ) {

    }

    record Response(
        ReadCustomer readCustomer
    ) {

    }

    class DefaultFindCustomerByIdentificationNumberUseCase implements FindCustomerByIdentificationNumberUseCase {
        private final CustomerRepository customerRepository;

        public DefaultFindCustomerByIdentificationNumberUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.identificationNumber() == null)
                throw new IllegalArgumentException("indentification number must not be null or empty");

            Customer customer = customerRepository.findByIdentificationNumber(requestData.identificationNumber())
                    .orElseThrow(() -> new IllegalArgumentException("indentification number not found"));

            return new DefaultFindCustomerByIdentificationNumberUseCase.Response(
                    ReadCustomer.from(customer)
            );
        }
    }
}