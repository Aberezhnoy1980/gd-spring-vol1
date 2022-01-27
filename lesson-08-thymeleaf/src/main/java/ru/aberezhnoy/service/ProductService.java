package ru.aberezhnoy.service;

import org.springframework.data.domain.Page;
import ru.aberezhnoy.service.dto.ProductDto;

import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort);

    Optional<ProductDto> findById(Long Id);

    ProductDto save(ProductDto product);

    void deleteById(Long id);
}
