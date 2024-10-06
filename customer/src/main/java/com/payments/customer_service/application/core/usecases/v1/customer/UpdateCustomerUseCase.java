package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.domain.v1.IdentificationNumber;
import com.payments.customer_service.application.core.domain.v1.PhoneNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface UpdateCustomerUseCase {

    Response execute(Request requestData);

    record Request(
        String ref,
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

    class DefaultUpdateCustomerUseCase implements UpdateCustomerUseCase {
        private final CustomerRepository customerRepository;

        public DefaultUpdateCustomerUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.ref == null) {
                throw new IllegalArgumentException("");
            } else {
                try {
                    UUID.fromString(requestData.ref);
                } catch (Exception e) {
                    throw new IllegalArgumentException("");
                }
            }
            if (requestData.fullName == null) {
                throw new IllegalArgumentException("");
            }
            if (requestData.identificationNumber == null) {
                throw new IllegalArgumentException("");
            }
            if (requestData.dateOfBirth == null) {
                throw new IllegalArgumentException("");
            }
            if (!customerRepository.exists(UUID.fromString(requestData.ref))) {
                throw new IllegalArgumentException("");
            }
            if (requestData.addressRef != null) {
                try {
                    UUID.fromString(requestData.addressRef);
                } catch (Exception e) {
                    throw new IllegalArgumentException("");
                }
            }

            Customer customer =
                    new Customer(
                            UUID.fromString(requestData.ref),
                            requestData.fullName,
                            requestData.identificationNumber,
                            requestData.dateOfBirth,
                            requestData.phoneNumber,
                            requestData.addressRef != null ? UUID.fromString(requestData.addressRef) : null
                    );

            return new Response(
                    customerRepository.save(customer)
            );
        }
    }
}