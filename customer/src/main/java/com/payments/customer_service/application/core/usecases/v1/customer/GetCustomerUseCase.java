package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.domain.v1.ReadCustomer;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;

public interface GetCustomerUseCase {

    Response execute(Request requestData);

    record Request(
            String ref
    ) {

    }

    record Response(
            ReadCustomer customer
    ) {

    }

    class DefaultGetCustomerUseCase implements GetCustomerUseCase {
        private final CustomerRepository customerRepository;

        public DefaultGetCustomerUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.ref == null) {
                throw new IllegalArgumentException("");
            } else {
                try {
                    UUID.fromString(requestData.ref);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("");
                }
            }

           Customer customer = customerRepository.get(UUID.fromString(requestData.ref))
                            .orElseThrow(() -> new IllegalArgumentException(""));

            return new GetCustomerUseCase.Response(
                    ReadCustomer.builder()
                            .ref(customer.id().toString())
                            .fullName(customer.fullName())
                            .identificationNumber(customer.identificationNumber().getNumber())
                            .phoneNumber(customer.phoneNumber().getNumber())
                            .dateOfBirth(customer.dateOfBirth().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))
                            .build()
            );
        }
    }
}