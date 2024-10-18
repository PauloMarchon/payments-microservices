package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.exception.ResourceNotFoundException;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;
import com.payments.customer_service.application.core.valueobjects.v1.Address;

import java.util.UUID;

public interface ChangeCustomerAddressUseCase {

    Response execute(Request requestData);

    record Request(
            UUID customerRef,
            Address address
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCustomerAddressUseCase implements ChangeCustomerAddressUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangeCustomerAddressUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.customerRef() == null)
                throw new IllegalArgumentException("CustomerRef is null");

            if (requestData.address() == null)
                throw new IllegalArgumentException("Address is null");

            Customer customer = customerRepository.findById(requestData.customerRef())
                    .orElseThrow(() -> new ResourceNotFoundException("CustomerRef not found"));

            customer.changeAddress(requestData.address());

            customerRepository.save(customer);

            return new ChangeCustomerAddressUseCase.Response();
        }
    }
}