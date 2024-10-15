package com.payments.customer_service.application.core.domain.v1;

import com.payments.customer_service.application.core.valueobjects.v1.Address;
import com.payments.customer_service.application.core.valueobjects.v1.Email;
import com.payments.customer_service.application.core.valueobjects.v1.IdentificationNumber;
import com.payments.customer_service.application.core.valueobjects.v1.PhoneNumber;

import java.time.LocalDate;
import java.util.UUID;

public class Company {
    private UUID id;
    private IdentificationNumber identificationNumber;
    private String companyName;
    private String fantasyName;
    private LocalDate openingDate;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private Customer customer;

    private Company(UUID id, IdentificationNumber identificationNumber, String companyName, String fantasyName, LocalDate openingDate, Email email, PhoneNumber phoneNumber, Address address, Customer customer) {
        this.id = id;
        this.identificationNumber = identificationNumber;
        this.companyName = companyName;
        this.fantasyName = fantasyName;
        this.openingDate = openingDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.customer = customer;
    }

    public static Company create(IdentificationNumber identificationNumber, String companyName, String fantasyName, LocalDate openingDate, Email email, PhoneNumber phoneNumber, Address address, Customer customer) {
        if (companyName == null || companyName.isEmpty())
            throw new IllegalArgumentException("Company name cannot be null or empty");

        if (fantasyName == null || fantasyName.isEmpty())
            throw new IllegalArgumentException("Fantasy name cannot be null or empty");

        if (openingDate == null || openingDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Opening date cannot be in the past");

        if (customer == null)
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
                customer
        );
    }

    public void changeCompanyName(String newCompanyName) {
        if (newCompanyName == null || newCompanyName.isEmpty())
            throw new IllegalArgumentException("Company name cannot be null or empty");

        this.companyName = newCompanyName;
    }

    public void changeFantasyName(String newFantasyName) {
        if (newFantasyName == null || newFantasyName.isEmpty())
            throw new IllegalArgumentException("Fantasy name cannot be null or empty");

        this.fantasyName = newFantasyName;
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

    public Customer getCustomer() {
        return customer;
    }
}
