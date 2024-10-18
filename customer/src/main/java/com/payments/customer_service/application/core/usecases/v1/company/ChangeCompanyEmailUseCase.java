package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;
import com.payments.customer_service.application.core.valueobjects.v1.Email;

import java.util.UUID;

public interface ChangeCompanyEmailUseCase {

    Response execute(Request requestData);

    record Request(
           UUID companyRef,
           Email email
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCompanyEmailUseCase implements ChangeCompanyEmailUseCase {
        private final CompanyRepository companyRepository;

        public DefaultChangeCompanyEmailUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("Company ref is required");

            if (requestData.email() == null)
                throw new IllegalArgumentException("Email is required");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("Company not found"));

            company.changeEmail(requestData.email());

            companyRepository.save(company);

            return new ChangeCompanyEmailUseCase.Response();
        }
    }
}