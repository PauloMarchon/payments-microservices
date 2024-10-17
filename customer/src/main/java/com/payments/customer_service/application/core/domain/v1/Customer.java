package com.payments.customer_service.application.core.domain.v1;

import com.payments.customer_service.application.core.valueobjects.v1.Address;
import com.payments.customer_service.application.core.valueobjects.v1.Email;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;

import java.time.LocalDate;
import java.util.UUID;

public class Customer implements Addressable {
    private final UUID id;
    private String fullName;
    private IdentificationNumber identificationNumber;
    private LocalDate birthDate;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private UUID companyRef;

    private Customer(UUID id, String fullName, IdentificationNumber identificationNumber, LocalDate birthDate, Email email, PhoneNumber phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.identificationNumber = identificationNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static Customer create(String fullName, IdentificationNumber identificationNumber, LocalDate birthDate, Email email, PhoneNumber phoneNumber) {
        if (fullName == null || fullName.isEmpty())
            throw new IllegalArgumentException("Full name cannot be null or empty");

        if (birthDate == null || birthDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date of birth cannot be in the future");

        return new Customer(
                UUID.randomUUID(),
                fullName,
                identificationNumber,
                birthDate,
                email,
                phoneNumber
        );
    }

    public void changeFullName(String fullName) {
        if (fullName == null || fullName.isEmpty())
            throw new IllegalArgumentException("The full name cannot be null or empty");

        this.fullName = fullName;
    }

    public void changeIdentificationNumber(IdentificationNumber identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void changeBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void changeEmail(Email email) {
        this.email = email;
    }

    public void changePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public LocalDate getBirthDate() {
        return birthDate;
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