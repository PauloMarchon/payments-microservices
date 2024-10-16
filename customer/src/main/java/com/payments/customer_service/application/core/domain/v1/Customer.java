package com.payments.customer_service.application.core.domain.v1;

import com.payments.customer_service.application.core.valueobjects.v1.Address;
import com.payments.customer_service.application.core.valueobjects.v1.Email;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;

import java.time.LocalDate;
import java.util.UUID;

public class Customer implements Addressable {
    private UUID id;
    private String fullName;
    private IdentificationNumber identificationNumber;
    private LocalDate dateOfBirth;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private UUID companyRef;

    private Customer(UUID id, String fullName, IdentificationNumber identificationNumber, LocalDate dateOfBirth, Email email, PhoneNumber phoneNumber, Address address, UUID companyRef) {
        this.id = id;
        this.fullName = fullName;
        this.identificationNumber = identificationNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.companyRef = companyRef;
    }

    public static Customer create(String fullName, IdentificationNumber identificationNumber, LocalDate dateOfBirth, Email email, PhoneNumber phoneNumber, Address address, UUID companyRef) {
        if (fullName == null || fullName.trim().isEmpty())
            throw new IllegalArgumentException("Full name cannot be null or empty");

        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date of birth cannot be in the future");

        return new Customer(
                UUID.randomUUID(),
                fullName,
                identificationNumber,
                dateOfBirth,
                email,
                phoneNumber,
                address,
                companyRef
        );
    }

    public void changeFullName(String newFullName) {
        if (newFullName == null || newFullName.isEmpty())
            throw new IllegalArgumentException("The full name cannot be null or empty");

        this.fullName = newFullName;
    }

    public void assignCompany(UUID companyRef) {
        if (companyRef == null)
            throw new IllegalArgumentException("Company cannot be null");

        this.companyRef = companyRef;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public IdentificationNumber getIdentificationNumber() {
        return identificationNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
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

    public UUID getCompanyRef() {
        return companyRef;
    }

    @Override
    public void changeAddress(Address newAddress) {
        if (newAddress == null)
            throw new IllegalArgumentException("Address cannot be null");

        this.address = newAddress;
    }
}
