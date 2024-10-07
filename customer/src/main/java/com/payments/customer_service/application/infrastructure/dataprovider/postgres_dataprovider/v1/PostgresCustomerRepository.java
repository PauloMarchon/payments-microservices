package com.payments.customer_service.application.infrastructure.dataprovider.postgres_dataprovider.v1;

import com.payments.customer_service.application.core.domain.v1.Customer;
import com.payments.customer_service.application.core.domain.v1.IdentificationNumber;
import com.payments.customer_service.application.core.domain.v1.PhoneNumber;
import com.payments.customer_service.application.core.usecases.repositories.v1.CustomerRepository;
import com.payments.customer_service.application.infrastructure.dataprovider.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostgresCustomerRepository implements CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostgresCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Customer> get(UUID ref) {
         jdbcTemplate.query("""
                SELECT *, a.ref AS address_ref
                FROM customer c
                LEFT JOIN address a ON a.id = c.address
                WHERE c.ref = ?
                """,
                List.of(ref.toString()),
                rs -> {
                    try {
                        return Optional.of(Customer.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .fullName(rs.getString("full_name"))
                                .identificationNumber((rs.getObject("identification_number", IdentificationNumber.class)))
                                .dateOfBirth(rs.getDate("birth_of_date").toLocalDate())
                                .phoneNumber(new PhoneNumber(rs.getString("phone_number")))
                                .addressRef(UUID.fromString(rs.getString("address_ref")))
                                .build()
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return Optional.empty();
    }

    @Override
    public String save(Customer customer) {
        if (exists(customer.id())) {
            if (customer.addressRef() == null) {
                jdbcTemplate.execute("""
                        UPDATE customer
                        SET ref = ?,
                        full_name = ?,
                        identification_number = ?,
                        date_of_birth = ?,
                        phone_number = ?,
                        WHERE ref = ?;
                        """,
                        List.of(
                                customer.id().toString(),
                                customer.fullName(),
                                customer.identificationNumber().getNumber(),
                                customer.dateOfBirth(),
                                customer.phoneNumber().getNumber(),
                                customer.id().toString()
                        )
                );
            } else {
                jdbcTemplate.execute("""
                        UPDATE customer
                        SET ref = ?,
                        full_name = ?,
                        identification_number = ?,
                        date_of_birth = ?,
                        phone_number = ?,
                        address = (SELECT id FROM address WHERE ref = ?)
                        WHERE red = ?;
                        """,
                        List.of(
                                customer.id().toString(),
                                customer.fullName(),
                                customer.identificationNumber().getNumber(),
                                customer.dateOfBirth(),
                                customer.phoneNumber().getNumber(),
                                customer.addressRef().toString(),
                                customer.id().toString()
                        )
                );
            }

        } else {
            if (customer.addressRef() == null) {
                jdbcTemplate.execute("""
                        INSERT INTO customer (ref, full_name, identification_number, date_of_birth, phone_number)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                        List.of(
                                customer.id().toString(),
                                customer.fullName(),
                                customer.identificationNumber().getNumber(),
                                customer.dateOfBirth(),
                                customer.phoneNumber().getNumber()
                        )
                );
            } else {
                jdbcTemplate.execute("""
                        INSERT INTO customer (ref, full_name, identification_number, date_of_birth, phone_number, address)
                        VALUES (?, ?, ?, ?, ?, (SELECT id FROM address WHERE ref =?))
                        """,
                        List.of(customer.id().toString(),
                                customer.fullName(),
                                customer.identificationNumber().getNumber(),
                                customer.dateOfBirth(),
                                customer.phoneNumber().getNumber(),
                                customer.addressRef().toString()
                        )
                );
            }
        }
        return customer.id().toString();
    }

    @Override
    public void delete(UUID ref) {
        jdbcTemplate.execute("""
                DELETE FROM customer WHERE ref = ?
                """,
                List.of(ref.toString())
        );
    }

    @Override
    public boolean exists(UUID ref) {
        Integer result = jdbcTemplate.query("""
                SELECT COUNT(*) AS c FROM customer WHERE ref = ?
                """,
                List.of(Collections.singletonList(ref.toString())),
                rs -> {
                    try {
                        if (rs.next()) {
                            return rs.getInt("c");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return 0;
                });

        return result != null && result > 0;
    }

    @Override
    public Optional<Customer> findByIdentificationNumber(IdentificationNumber identificationNumber) {
          jdbcTemplate.query("""
                SELECT * FROM customer WHERE identification_number = ?
                """,
                List.of(identificationNumber.getNumber()),
                rs -> {
                    try {
                        return Optional.of(Customer.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .fullName(rs.getString("full_name"))
                                .identificationNumber(identificationNumber.identificationType(rs.getString("identification_number")))
                                .dateOfBirth(rs.getDate("birth_of_date").toLocalDate())
                                .phoneNumber(new PhoneNumber(rs.getString("phone_number")))
                                .build()
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return Optional.empty();
    }

    @Override
    public void updatePhoneNumber(UUID ref, PhoneNumber phoneNumber) {
        jdbcTemplate.execute("""
                UPDATE customer
                SET phone_number = ?
                WHERE ref = ?
                """,
                List.of(phoneNumber.getNumber(), ref.toString())
        );
    }

    @Override
    public void updateIdentificationNumber(UUID ref, IdentificationNumber identificationNumber) {
        jdbcTemplate.execute("""
                UPDATE customer
                SET identification_number = ?
                WHERE ref = ?
                """,
                List.of(identificationNumber.getNumber(), ref.toString())
        );
    }
}