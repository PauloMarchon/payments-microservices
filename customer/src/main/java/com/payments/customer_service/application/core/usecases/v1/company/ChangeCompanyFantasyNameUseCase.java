package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;

import java.util.UUID;

public interface ChangeCompanyFantasyNameUseCase {

    Response execute(Request requestData);

    record Request(
            UUID companyRef,
            String fantasyName
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCompanyFantasyNameUseCase implements ChangeCompanyFantasyNameUseCase {
        private final CompanyRepository companyRepository;

        public DefaultChangeCompanyFantasyNameUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("companyRef is null");

            if (requestData.fantasyName() == null)
                throw new IllegalArgumentException("fantasyName is null");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("Company not found"));

            company.changeFantasyName(requestData.fantasyName());

            companyRepository.save(company);

            return new ChangeCompanyFantasyNameUseCase.Response();
        }
    }
}