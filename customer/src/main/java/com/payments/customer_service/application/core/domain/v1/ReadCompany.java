package com.payments.customer_service.application.core.domain.v1;

import lombok.Builder;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Builder
public record ReadCompany(
        String ref,
        String identificationNumber,
        String companyName,
        String fantasyName,
        String openingDate,
        String email,
        String phoneNumber,
        ReadAddress address
) {

    public static ReadCompany from(Company company) {
        if (company == null)
            throw new NullPointerException("company is null");

        ReadAddress readAddress = ReadAddress.from(company.getAddress());

        return new ReadCompany(
                company.getId().toString(),
                company.getIdentificationNumber().getNumber(),
                company.getCompanyName(),
                company.getFantasyName(),
                company.getOpeningDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                company.getEmail().getEmail(),
                company.getPhoneNumber().getNumber(),
                readAddress
        );
    }
}
