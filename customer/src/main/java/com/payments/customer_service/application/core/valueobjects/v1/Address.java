package com.payments.customer_service.application.core.valueobjects.v1;

import lombok.Builder;

@Builder
public class Address {
    private String zipCode;
    private String street;
    private String district;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String country;

    public Address(String zipCode, String street, String district, Integer number, String complement, String city, String state, String country) {
        this.zipCode = zipCode;
        this.street = street;
        this.district = district;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
