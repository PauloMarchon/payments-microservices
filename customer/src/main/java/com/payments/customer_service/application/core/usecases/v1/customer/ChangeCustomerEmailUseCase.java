package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.exception.ResourceNotFoundException;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;
import com.payments.customer_service.application.core.valueobjects.v1.Email;

import java.util.UUID;

public interface ChangeCustomerEmailUseCase {

    Response execute(Request requestData);

    record Request(
            UUID customerRef,
            Email email
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeEmailUseCase implements ChangeCustomerEmailUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangeEmailUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.customerRef == null)
                throw new IllegalArgumentException("CustomerRef is null");

            Customer customer = customerRepository.findById(requestData.customerRef)
                    .orElseThrow(() -> new ResourceNotFoundException("CustomerRef not found"));

            customer.changeEmail(requestData.email);

            customerRepository.save(customer);

            return new ChangeCustomerEmailUseCase.Response();
        }
    }
}