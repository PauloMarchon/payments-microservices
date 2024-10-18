package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;
import com.payments.customer_service.application.core.valueobjects.v1.Address;

import java.util.UUID;

public interface ChangeCompanyAddressUseCase {
    Response execute(Request requestData);

    record Request(
            UUID companyRef,
            Address address
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultChangeCompanyAddressUseCase implements ChangeCompanyAddressUseCase {
        private final CompanyRepository companyRepository;

        public DefaultChangeCompanyAddressUseCase(CompanyRepository companyRepository) {

            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("CustomerRef is null");

            if (requestData.address() == null)
                throw new IllegalArgumentException("Address is null");

            Company company = companyRepository.findById(requestData.companyRef())
                    .orElseThrow(() -> new IllegalArgumentException("CustomerRef not found"));

            company.changeAddress(requestData.address());

            companyRepository.save(company);

            return new ChangeCompanyAddressUseCase.Response();
        }
    }
}