package com.payments.customer_service.application.infrastructure.dataprovider.core;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public interface JdbcTemplate {
    void execute(String query, List<Object> params);

    <T> T query(String query, List<Object> params, Function<ResultSet, T> mapper) throws JdbcException;

    <T> List<T> queryForList(String query, List<Object> params, Function<ResultSet, T> mapper) throws JdbcException;

    class JdbcException extends RuntimeException {
        String message;

        public JdbcException(String message) {
            super(message);
        }
    }
}
