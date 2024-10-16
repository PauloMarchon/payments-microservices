package com.payments.customer_service.application.core.domain.v1;

import com.payments.customer_service.application.core.valueobjects.v1.Address;
import com.payments.customer_service.application.core.valueobjects.v1.Email;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;

import java.time.LocalDate;
import java.util.UUID;

public class Company implements Addressable {
    private final UUID id;
    private IdentificationNumber identificationNumber;
    private String companyName;
    private String fantasyName;
    private LocalDate openingDate;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private UUID customerRef;

    private Company(UUID id, IdentificationNumber identificationNumber, String companyName, String fantasyName, LocalDate openingDate, Email email, PhoneNumber phoneNumber, Address address, UUID customerRef) {
        this.id = id;
        this.identificationNumber = identificationNumber;
        this.companyName = companyName;
        this.fantasyName = fantasyName;
        this.openingDate = openingDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.customerRef = customerRef;
    }

    public static Company create(IdentificationNumber identificationNumber, String companyName, String fantasyName, LocalDate openingDate, Email email, PhoneNumber phoneNumber, Address address, UUID customerRef) {
        if (companyName == null || companyName.isEmpty())
            throw new IllegalArgumentException("Company name cannot be null or empty");

        if (fantasyName == null || fantasyName.isEmpty())
            throw new IllegalArgumentException("Fantasy name cannot be null or empty");

        if (openingDate == null || openingDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Opening date cannot be in the past");

        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        if (customerRef == null)
            throw new IllegalArgumentException("Customer cannot be null");

        return new Company(
                UUID.randomUUID(),
                identificationNumber,
                companyName,
                fantasyName,
                openingDate,
                email,
                phoneNumber,
                address,
                customerRef
        );
    }

    public void changeIdentificationNumber(IdentificationNumber identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void changeCompanyName(String companyName) {
        if (companyName == null || companyName.isEmpty())
            throw new IllegalArgumentException("Company name cannot be null or empty");

        this.companyName = companyName;
    }

    public void changeFantasyName(String fantasyName) {
        if (fantasyName == null || fantasyName.isEmpty())
            throw new IllegalArgumentException("Fantasy name cannot be null or empty");

        this.fantasyName = fantasyName;
    }

    public void changeOpeningDate(LocalDate openingDate) {
        if (openingDate == null || openingDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Opening date cannot be in the past");

        this.openingDate = openingDate;
    }

    public void changeEmail(Email email) {
        this.email = email;
    }

    public void changePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public IdentificationNumber getIdentificationNumber() {
        return identificationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public UUID getCustomerRef() {
        return customerRef;
    }

    @Override
    public void changeAddress(Address newAddress) {
        if (newAddress == null)
            throw new IllegalArgumentException("Address cannot be null");

        this.address = newAddress;
    }
}