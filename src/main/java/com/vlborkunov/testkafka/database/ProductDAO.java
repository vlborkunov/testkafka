package com.vlborkunov.testkafka.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.vlborkunov.testkafka.database.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO {

    //@Autowired
    //private ProductMapper mapper;
    private RowMapper<Product> mapper = BeanPropertyRowMapper.newInstance(Product.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<Product> findAll() {
        return jdbc.query(
                "SELECT * from product ORDER BY id",
                mapper
        );
    }

    public Optional<Product> findById(Long id) {
        Product product = jdbc.getJdbcTemplate().queryForObject(
                "SELECT * from product where id = ?",
                mapper,
                id
        );
        return Optional.of(product);
    }

    public Product insert(Product product) {
        jdbc.update(
                "INSERT INTO product(id, name, price) VALUES (:id, :name, :price)",
                new BeanPropertySqlParameterSource(product)
        );
        return product;
    }

    public void deleteById(Long id) {
        jdbc.getJdbcTemplate().update(
                "DELETE FROM product WHERE id = ?",
                id);
    }

}
