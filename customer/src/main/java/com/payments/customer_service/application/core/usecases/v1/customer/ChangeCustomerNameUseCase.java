package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.exception.ResourceNotFoundException;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface ChangeCustomerNameUseCase {

    Response execute(Request requestData);

    record Request(
            UUID customerRef,
            String fullName
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCustomerNameUseCase implements ChangeCustomerNameUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangeCustomerNameUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.customerRef == null)
                throw new IllegalArgumentException("CustomerRef is null");

            if (requestData.fullName == null || requestData.fullName.isEmpty())
                throw new IllegalArgumentException("FullName is null or empty");

            Customer customer = customerRepository.findById(requestData.customerRef)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            customer.changeFullName(requestData.fullName);

            customerRepository.save(customer);

            return new ChangeCustomerNameUseCase.Response();
        }
    }
}
