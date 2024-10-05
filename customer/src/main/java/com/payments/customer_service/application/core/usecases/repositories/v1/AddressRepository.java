package com.payments.customer_service.application.core.usecases.repositories.v1;

import com.payments.customer_service.application.core.domain.v1.Address;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository {

    Optional<Address> get(UUID ref);
    String save(Address address);
    void delete(UUID ref);
    boolean exists(UUID ref);
}
