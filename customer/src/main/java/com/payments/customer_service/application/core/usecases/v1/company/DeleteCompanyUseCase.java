package com.payments.customer_service.application.core.usecases.v1.company;

import com.payments.customer_service.application.core.usecases.repositories.v1.CompanyRepository;

import java.util.UUID;

public interface DeleteCompanyUseCase {

    Response execute(Request requestData);

    record Request(
            UUID companyRef
    ) {

    }

    record Response(

    ) {

    }

    public class DefaultDeleteCompanyUseCase implements DeleteCompanyUseCase {
        private final CompanyRepository companyRepository;

        public DefaultDeleteCompanyUseCase(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public Response execute(Request requestData) {
            if (requestData.companyRef() == null)
                throw new IllegalArgumentException("companyRef is null");

            companyRepository.delete(requestData.companyRef());

            return new DeleteCompanyUseCase.Response();
        }
    }
}
