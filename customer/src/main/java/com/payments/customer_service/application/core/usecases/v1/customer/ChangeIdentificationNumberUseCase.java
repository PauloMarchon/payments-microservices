package com.payments.customer_service.application.core.usecases.v1.customer;

import com.payments.customer_service.application.core.domain.v1.IdentificationNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;

import java.util.UUID;

public interface ChangeIdentificationNumberUseCase {

    Response execute(Request requestData);

    record Request(
        String ref,
        IdentificationNumber identificationNumber
    ) {

    }

    record Response(

    ) {

    }

    class DefaultChangeIdentificationNumberUseCase implements ChangeIdentificationNumberUseCase {
        private final CustomerRepository customerRepository;

        public DefaultChangeIdentificationNumberUseCase(CustomerRepository customerRepository) {
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

            if (requestData.identificationNumber == null)
                throw new IllegalArgumentException("");

            if (requestData.identificationNumber.getNumber() == null || requestData.identificationNumber.getNumber().isEmpty())
                throw new IllegalArgumentException("");

            customerRepository.updateIdentificationNumber(UUID.fromString(requestData.ref), requestData.identificationNumber);

            return new ChangeIdentificationNumberUseCase.Response();
        }
    }
}
