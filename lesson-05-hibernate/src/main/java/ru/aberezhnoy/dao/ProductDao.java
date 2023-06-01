package ru.aberezhnoy.dao;

import ru.aberezhnoy.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    Product saveOrUpdate(Product product);
}
