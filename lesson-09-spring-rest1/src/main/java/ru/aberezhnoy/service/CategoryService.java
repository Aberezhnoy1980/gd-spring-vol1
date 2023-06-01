package ru.aberezhnoy.service;

import ru.aberezhnoy.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> findAll();

    Optional<CategoryDto> findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    void deleteById(Long id);
}
