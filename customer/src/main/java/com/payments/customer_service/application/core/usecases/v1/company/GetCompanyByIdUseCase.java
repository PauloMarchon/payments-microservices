package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.domain.v1.ReadCompany;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;

import java.util.UUID;

public interface GetCompanyByIdUseCase {

    Response execute(Request requestData);

    record Request(
            UUID companyRef
    ) {

    }

    record Response(
            ReadCompany readCompany
    ) {

    }

    public class DefaultGetCompanyByIdUseCase implements GetCompanyByIdUseCase {
        private final CompanyRepository companyRepository;

        public DefaultGetCompanyByIdUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("companyRef is null");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("companyRef not found"));

            return new GetCompanyByIdUseCase.Response(
                    ReadCompany.from(company)
            );
        }
    }
}