package com.payments.customer_service.application.core.usecases.repositories.v1;

import com.payments.customer_service.application.core.domain.v1.Company;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {
    Optional<Company> findById(UUID ref);
    Optional<Company> findByIdentificationNumber(IdentificationNumber identificationNumber);
    String save(Company company);
    void delete(UUID ref);
}
