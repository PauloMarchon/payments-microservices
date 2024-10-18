package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.exception.ResourceNotFoundException;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface ChangeCustomerIdentificationNumberUseCase {

    Response execute(Request requestData);

    record Request(
        UUID customerRef,
        IdentificationNumber identificationNumber
    ) {

    }

    record Response(

    ) {

    }

    class DefaultChangeCustomerIdentificationNumberUseCase implements ChangeCustomerIdentificationNumberUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangeCustomerIdentificationNumberUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if(requestData.customerRef() == null)
                throw new IllegalArgumentException("CustomerRef is null");

            Customer customer = customerRepository.findById(requestData.customerRef())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            customer.changeIdentificationNumber(requestData.identificationNumber());

            customerRepository.save(customer);

            return new ChangeCustomerIdentificationNumberUseCase.Response();
        }
    }
}
