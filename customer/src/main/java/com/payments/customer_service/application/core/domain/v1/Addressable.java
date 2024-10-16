package com.payments.customer_service.application.core.domain.v1;

import com.payments.customer_service.application.core.valueobjects.v1.Address;

public interface Addressable {
    void changeAddress(Address newAddress);
}
