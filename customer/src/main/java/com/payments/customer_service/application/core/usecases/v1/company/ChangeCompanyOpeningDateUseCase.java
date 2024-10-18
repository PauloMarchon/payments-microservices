package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface ChangeCompanyOpeningDateUseCase {

    Response execute(Request requestData);

    record Request(
            UUID companyRef,
            LocalDate openingDate
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCompanyOpeningDateUseCase implements ChangeCompanyOpeningDateUseCase {
        private final CompanyRepository companyRepository;

        public DefaultChangeCompanyOpeningDateUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("companyRef is null");

            if (requestData.openingDate() == null || requestData.openingDate().isAfter(LocalDate.now()))
                throw new IllegalArgumentException("openingDate is null or openingDate is after current date");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("Company not found"));

            company.changeOpeningDate(requestData.openingDate());

            companyRepository.save(company);

            return new ChangeCompanyOpeningDateUseCase.Response();
        }
    }
}