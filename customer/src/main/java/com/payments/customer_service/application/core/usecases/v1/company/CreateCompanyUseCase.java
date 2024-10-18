package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;
import com.payments.customer_service.application.core.valueobjects.v1.Address;
import com.payments.customer_service.application.core.valueobjects.v1.Email;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;

import java.time.LocalDate;
import java.util.UUID;

public interface CreateCompanyUseCase {

    Response execute(Request requestData);

    record Request(
            IdentificationNumber identificationNumber,
            String companyName,
            String fantasyName,
            LocalDate openingDate,
            Email email,
            PhoneNumber phoneNumber,
            Address address,
            UUID customerRef
    ) {

    }

    record Response(
            String ref
    ) {

    }

    public class DefaultCreateCompanyUseCase implements CreateCompanyUseCase {
        private final CompanyRepository companyRepository;

        public DefaultCreateCompanyUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {

            Company company = Company.create(
                    requestData.identificationNumber(),
                    requestData.companyName(),
                    requestData.fantasyName(),
                    requestData.openingDate(),
                    requestData.email(),
                    requestData.phoneNumber(),
                    requestData.address(),
                    requestData.customerRef()
            );

            return new CreateCompanyUseCase.Response(
                    companyRepository.save(company)
            );
        }
    }
}