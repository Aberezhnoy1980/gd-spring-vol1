package ru.aberezhnoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.aberezhnoy.persist.*;
import ru.aberezhnoy.service.dto.ProductDto;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductDto> findAll(Optional<String> nameFilter, Optional<BigDecimal> minPriceFilter, Optional<BigDecimal> maxPriceFilter, Integer page, Integer size, String sort) {
        Specification<Product> spec = Specification.where(null);
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
           spec = spec.and(ProductSpecification.nameLike(nameFilter.get()));
        }
        if (minPriceFilter.isPresent()) {
           spec = spec.and(ProductSpecification.minPrice(minPriceFilter.get()));
        }
        if (maxPriceFilter.isPresent()) {
           spec = spec.and(ProductSpecification.maxPrice(maxPriceFilter.get()));
        }
        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sort)))
                .map(ProductServiceImpl::convertToDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductServiceImpl::convertToDto);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElse(null);
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(), category);
        Product saved = productRepository.save(product);
        return convertToDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private static ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }
}
