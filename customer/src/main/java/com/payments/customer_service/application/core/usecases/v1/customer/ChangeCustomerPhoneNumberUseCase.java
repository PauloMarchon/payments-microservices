package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.exception.ResourceNotFoundException;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface ChangeCustomerPhoneNumberUseCase {

    Response execute (Request requestData);

    record Request(
            UUID customerRef,
            PhoneNumber phoneNumber
    ) {

    }

    record Response(

    ) {

    }

    class DefaultChangeCustomerPhoneNumberUseCase implements ChangeCustomerPhoneNumberUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangeCustomerPhoneNumberUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if(requestData.customerRef() == null)
                throw new IllegalArgumentException("CustomerRef is null");

            Customer customer = customerRepository.findById(requestData.customerRef())
                    .orElseThrow(() -> new ResourceNotFoundException(""));

            customer.changePhoneNumber(requestData.phoneNumber());

            customerRepository.save(customer);

            return new ChangeCustomerPhoneNumberUseCase.Response();
        }
    }
}