package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;

import java.util.UUID;

public interface ChangeCompanyPhoneNumberUseCase {

    Response execute(Request requestData);

    record Request(
            UUID companyRef,
            PhoneNumber phoneNumber
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCompanyPhoneNumberUseCase implements ChangeCompanyPhoneNumberUseCase {
        private final CompanyRepository companyRepository;

        public DefaultChangeCompanyPhoneNumberUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("companyRef is null");

            if (requestData.phoneNumber() == null)
                throw new IllegalArgumentException("phoneNumber is null");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("companyRef not found"));

            company.changePhoneNumber(requestData.phoneNumber());

            companyRepository.save(company);

            return new ChangeCompanyPhoneNumberUseCase.Response();
        }
    }
}