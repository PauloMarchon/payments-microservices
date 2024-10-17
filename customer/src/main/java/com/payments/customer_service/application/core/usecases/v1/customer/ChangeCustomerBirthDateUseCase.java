package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.exception.ResourceNotFoundException;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface ChangeCustomerBirthDateUseCase {

    Response execute(Request requestData);

    record Request(
            UUID customerRef,
            LocalDate birthDate
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCustomerBirthDateUseCase implements ChangeCustomerBirthDateUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangeCustomerBirthDateUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.customerRef == null)
                throw new IllegalArgumentException("CustomerRef is null");

            if (requestData.birthDate == null || requestData.birthDate.isAfter(LocalDate.now()))
                throw new IllegalArgumentException("BirthDate is invalid");

            Customer customer = customerRepository.findById(requestData.customerRef)
                    .orElseThrow(() -> new ResourceNotFoundException("CustomerRef not found"));

            customer.changeBirthDate(requestData.birthDate);

            customerRepository.save(customer);

            return new ChangeCustomerBirthDateUseCase.Response();
        }
    }
}