package ru.aberezhnoy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aberezhnoy.controller.NotFoundException;
import ru.aberezhnoy.service.CategoryService;
import ru.aberezhnoy.service.dto.CategoryDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryResource {
    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") Long id) {
        return categoryService.findById(id).orElseThrow(() -> new NotFoundException(String.format("Category with id %d not fount", id)));
    }

    @PutMapping
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() == null)
            throw new IllegalArgumentException("For category update id have to be not null");
        return categoryService.save(categoryDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() != null) throw new IllegalArgumentException("For category create id have to be null");
        return categoryService.save(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundExceptionHandler(NotFoundException exception) {
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new Error(exception.getMessage());
    }
}
