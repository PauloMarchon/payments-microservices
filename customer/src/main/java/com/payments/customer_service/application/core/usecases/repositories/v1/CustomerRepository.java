package com.payments.customer_service.application.core.usecases.repositories.v1;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findById(UUID ref);
    Optional<Customer> findByIdentificationNumber(IdentificationNumber identificationNumber);
    String save (Customer customer);
    void delete (UUID ref);
    boolean exists(UUID ref);
}
