package com.payments.customer_service.application.core.domain.v1;

import com.payments.customer_service.application.core.valueobjects.v1.Address;
import com.payments.customer_service.application.core.valueobjects.v1.Email;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {
    private UUID id;
    private String fullName;
    private IdentificationNumber identificationNumber;
    private LocalDate dateOfBirth;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private Company company;

    private Customer(UUID id, String fullName, IdentificationNumber identificationNumber, LocalDate dateOfBirth, Email email, PhoneNumber phoneNumber, Address address, Company company) {
        this.id = id;
        this.fullName = fullName;
        this.identificationNumber = identificationNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.company = company;
    }

    public static Customer create(String fullName, IdentificationNumber identificationNumber, LocalDate dateOfBirth, Email email, PhoneNumber phoneNumber, Address address, Company company) {
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
                company
        );
    }

    public void changeFullName(String newFullName) {
        if (newFullName == null || newFullName.isEmpty())
            throw new IllegalArgumentException("The full name cannot be null or empty");

        this.fullName = newFullName;
    }

    public void assignCompany(Company company) {
        if (company == null)
            throw new IllegalArgumentException("Company cannot be null");

        this.company = company;
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

    public Company getCompany() {
        return company;
    }
}
