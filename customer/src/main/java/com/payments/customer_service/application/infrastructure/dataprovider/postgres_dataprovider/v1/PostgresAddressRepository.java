package com.payments.customer_service.application.infrastructure.dataprovider.postgres_dataprovider.v1;

import com.payments.customer_service.application.core.domain.v1.Address;
import com.payments.customer_service.application.core.usecases.repositories.v1.AddressRepository;
import com.payments.customer_service.application.infrastructure.dataprovider.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostgresAddressRepository implements AddressRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostgresAddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Address> get(UUID ref) {
        var getQuery = """
                SELECT * FROM address WHERE ref = ?
                """;

        return jdbcTemplate.query(
                getQuery,
                List.of(ref.toString()),
                rs -> {
                    try {
                        if (rs.next()) {
                                return Optional.of(new Address(
                                        UUID.fromString(rs.getString("ref")),
                                        rs.getString("zip_code"),
                                        rs.getString("street"),
                                        rs.getString("district"),
                                        rs.getInt("number"),
                                        rs.getString("complement"),
                                        rs.getString("city"),
                                        rs.getString("state"),
                                        rs.getString("country"))
                                );
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return Optional.empty();
                });
    }

    @Override
    public String save(Address address) {
        var updateQuery = """
                            UPDATE address
                            SET ref = ?,
                                zip_code = ?,
                                street = ?,
                                district = ?,
                                number = ?,
                                complement = ?,
                                city = ?,
                                state = ?,
                                country = ?
                            WHERE ref = ?
                            """;

        var insertQuery = """
                            INSERT INTO address (ref, zip_code, street, district, number, complement, city, state, country)
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                            """;

        if (exists(address.id())) {
            jdbcTemplate.execute(
                    updateQuery,
                    List.of(
                            address.id().toString(),
                            address.zipCode(),
                            address.street(),
                            address.district(),
                            address.number(),
                            address.complement(),
                            address.city(),
                            address.state(),
                            address.country()
                    )
            );
        } else {
            jdbcTemplate.execute(
                    insertQuery,
                    List.of(
                            address.id().toString(),
                            address.zipCode(),
                            address.street(),
                            address.district(),
                            address.number(),
                            address.complement(),
                            address.city(),
                            address.state(),
                            address.country()
                    )
            );
        }
        return address.id().toString();
    }

    @Override
    public void delete(UUID ref) {
        var deleteQuery = """
                DELETE FROM address WHERE ref = ?
                """;

        jdbcTemplate.execute(deleteQuery, List.of(ref.toString()));
    }

    @Override
    public boolean exists(UUID ref) {
        var existsQuery = """
                SELECT COUNT(*) AS c FROM address WHERE ref = ?
                """;

        Integer result = jdbcTemplate.query(
                existsQuery,
                Collections.singletonList(ref.toString()),
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
}
