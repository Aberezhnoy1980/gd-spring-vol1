package ru.aberezhnoy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aberezhnoy.controller.NotFoundException;
import ru.aberezhnoy.service.ProductService;
import ru.aberezhnoy.service.dto.ProductDto;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> search(@RequestParam("nameFilter") Optional<String> nameFilter,
                                   @RequestParam("minPriceFilter") Optional<BigDecimal> minPriceFilter,
                                   @RequestParam("maxPriceFilter") Optional<BigDecimal> maxPriceFilter,
                                   @RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam("sort") Optional<String> sort) {
        return productService.findAll(
                nameFilter,
                minPriceFilter,
                maxPriceFilter,
                page.orElse(1) - 1,
                size.orElse(5),
                sort.filter(s -> !s.isBlank()).orElse("id"));
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        if (productDto.getId() == null) throw new IllegalArgumentException("For product update id have to be not null");
        return productService.save(productDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto productDto) {
        productDto.setId(0L);
        return productService.save(productDto);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundExceptionHandler(NotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
