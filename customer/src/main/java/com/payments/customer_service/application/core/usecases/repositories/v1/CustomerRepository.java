package com.payments.customer_service.application.core.usecases.repositories.v1;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.domain.v1.IdentificationNumber;
import com.payments.customer_service.application.core.domain.v1.PhoneNumber;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> get (UUID ref);
    String save (Customer customer);
    void delete (UUID ref);
    boolean exists(UUID ref);
    Optional<Customer> findByIdentificationNumber(IdentificationNumber identificationNumber);
    void updatePhoneNumber(UUID ref, PhoneNumber phoneNumber);
    void updateIdentificationNumber(UUID ref, IdentificationNumber identificationNumber);
}
