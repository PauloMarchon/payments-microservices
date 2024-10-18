package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;

import java.util.UUID;

public interface ChangeCompanyIdentificationNumberUseCase {

    Response execute(Request requestData);

    record Request(
            UUID companyRef,
            IdentificationNumber identificationNumber
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCompanyIdentificationNumberUseCase implements ChangeCompanyIdentificationNumberUseCase {
        private final CompanyRepository companyRepository;

        public DefaultChangeCompanyIdentificationNumberUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("companyRef is null");

            if (requestData.identificationNumber() == null)
                throw new IllegalArgumentException("identificationNumber is null");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("Company not found"));

            company.changeIdentificationNumber(requestData.identificationNumber());

            companyRepository.save(company);

            return new ChangeCompanyIdentificationNumberUseCase.Response();
        }
    }
}