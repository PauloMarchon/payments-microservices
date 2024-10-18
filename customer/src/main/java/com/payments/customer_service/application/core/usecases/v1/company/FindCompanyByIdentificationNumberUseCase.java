package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.domain.v1.ReadCompany;
import com.payments.customer_service.application.core.exception.InvalidIdentificationNumberException;
import com.payments.customer_service.application.core.exception.ResourceNotFoundException;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;

public interface FindCompanyByIdentificationNumberUseCase {

    Response execute (Request requestData);

    record Request(
           IdentificationNumber identificationNumber
    ) {

    }

    record Response(
            ReadCompany readCompany
    ) {

    }

    public class DefaultFindCompanyByIdentificationNumberUseCase implements FindCompanyByIdentificationNumberUseCase {
        private final CompanyRepository companyRepository;

        public DefaultFindCompanyByIdentificationNumberUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.identificationNumber() == null)
                throw new InvalidIdentificationNumberException("Identification number is required");

            Company company = companyRepository.findByIdentificationNumber(requestData.identificationNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

            return new FindCompanyByIdentificationNumberUseCase.Response(
                    ReadCompany.from(company)
            );
        }
    }
}