package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;

import java.util.UUID;

public interface ChangeCompanyNameUseCase {

    Response execute(Request requestData);

    record Request(
            UUID companyRef,
            String companyName
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCompanyNameUseCase implements ChangeCompanyNameUseCase {
        private final CompanyRepository companyRepository;

        public DefaultChangeCompanyNameUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("companyRef is null");

            if (requestData.companyName() == null)
                throw new IllegalArgumentException("companyName is null");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("companyRef not found"));

            company.changeCompanyName(requestData.companyName());

            companyRepository.save(company);

            return new Response();
        }
    }
}