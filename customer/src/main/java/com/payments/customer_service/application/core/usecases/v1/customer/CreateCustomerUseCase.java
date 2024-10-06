package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.domain.v1.IdentificationNumber;
import com.payments.customer_service.application.core.domain.v1.PhoneNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface CreateCustomerUseCase {

    Response execute(Request requestData);

    record Request(
            String fullName,
            IdentificationNumber identificationNumber,
            PhoneNumber phoneNumber,
            LocalDate dateOfBirth,
            String addressRef
    ) {

    }

    record Response(
            String ref
    ) {

    }

    class DefaultCreateCustomerUseCase implements CreateCustomerUseCase {
        private final CustomerRepository customerRepository;

        public DefaultCreateCustomerUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public CreateCustomerUseCase.Response execute(CreateCustomerUseCase.Request requestData) {
            if (requestData.fullName == null || requestData.fullName.isEmpty()) {
                throw new IllegalArgumentException("Full name cannot be null or empty");
            }

            if (requestData.identificationNumber == null) {
                throw new IllegalArgumentException("Identification number cannot be null");
            }

            if (requestData.dateOfBirth == null) {
                throw new IllegalArgumentException("Date of birth cannot be null");
            }

            if (requestData.addressRef != null) {
                try {
                    UUID.fromString(requestData.addressRef);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid address ref", e);
                }
            }

            Customer customer = Customer.builder()
                    .id(UUID.randomUUID())
                    .fullName(requestData.fullName)
                    .identificationNumber(requestData.identificationNumber)
                    .dateOfBirth(requestData.dateOfBirth)
                    .phoneNumber(requestData.phoneNumber)
                    .build();

            return new CreateCustomerUseCase.Response(
                    customerRepository.save(customer)
            );
        }
    }
}