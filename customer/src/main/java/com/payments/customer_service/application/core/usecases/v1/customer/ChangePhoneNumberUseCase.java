package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.PhoneNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface ChangePhoneNumberUseCase {

    Response execute (Request requestData);

    record Request(
            String ref,
            PhoneNumber phoneNumber
    ) {

    }

    record Response(

    ) {

    }

    class DefaultChangePhoneNumberUseCase implements ChangePhoneNumberUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangePhoneNumberUseCase(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if(requestData.ref == null) {
                throw new IllegalArgumentException("");
            } else {
                try {
                    UUID.fromString(requestData.ref);
                } catch (Exception e) {
                    throw new IllegalArgumentException("");
                }
            }

            if (requestData.phoneNumber == null || requestData.phoneNumber.getNumber() == null)
                throw new IllegalArgumentException("");

            if (!customerRepository.exists(UUID.fromString(requestData.ref)))
                throw new IllegalArgumentException("");

            customerRepository.updatePhoneNumber(UUID.fromString(requestData.ref), requestData.phoneNumber);

            return new ChangePhoneNumberUseCase.Response();
        }
    }
}
