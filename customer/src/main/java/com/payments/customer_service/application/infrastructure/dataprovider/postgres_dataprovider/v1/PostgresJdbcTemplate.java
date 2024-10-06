package com.payments.customer_service.application.infrastructure.dataprovider.postgres_dataprovider.v1;

import com.payments.customer_service.application.infrastructure.dataprovider.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PostgresJdbcTemplate implements JdbcTemplate {
    private static final Logger log = LoggerFactory.getLogger(PostgresJdbcTemplate.class);
    private String connectionUrl;
    private String username;
    private String password;

    @Override
    public void execute(String query, List<Object> params) {
        try (Connection conn = DriverManager.getConnection(connectionUrl, username, password)) {
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ps.execute();
        } catch (Exception e) {
            log.error("Error trying to execute query: {}", query, e);
            throw new JdbcException(e.getLocalizedMessage());
        }
    }

    @Override
    public <T> T query(String query, List<Object> params, Function<ResultSet, T> mapper) throws JdbcException {
        try (Connection conn = DriverManager.getConnection(connectionUrl, username, password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.apply(resultSet);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("Error trying to execute query: {}", query, e);
            throw new JdbcException(e.getLocalizedMessage());
        }
    }

    @Override
    public <T> List<T> queryForList(String query, List<Object> params, Function<ResultSet, T> mapper) throws JdbcException {
        try (Connection conn = DriverManager.getConnection(connectionUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            List<T> result = new ArrayList<>();
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    result.add(mapper.apply(resultSet));
                }
            }
            return result;

        } catch (Exception e) {
            log.error("Error trying to execute query: {}", query, e);
            throw new JdbcException(e.getLocalizedMessage());
        }
    }
}