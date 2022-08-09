package com.vlborkunov.testkafka.database.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.vlborkunov.testkafka.database.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                );
    }
}
