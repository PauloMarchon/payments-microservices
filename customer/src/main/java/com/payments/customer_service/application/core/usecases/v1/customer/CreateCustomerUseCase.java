package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.valueobjects.v1.Email;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.time.LocalDate;

public interface CreateCustomerUseCase {

    Response execute(Request requestData);

    record Request(
            String fullName,
            IdentificationNumber identificationNumber,
            LocalDate birthDate,
            Email email,
            PhoneNumber phoneNumber
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
            Customer customer = Customer.create(
                    requestData.fullName,
                    requestData.identificationNumber,
                    requestData.birthDate,
                    requestData.email,
                    requestData.phoneNumber
            );

            return new CreateCustomerUseCase.Response(
                    customerRepository.save(customer)
            );
        }
    }
}