package ru.aberezhnoy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;
import ru.aberezhnoy.persist.Product;
import ru.aberezhnoy.persist.ProductRepository;

import javax.annotation.PostConstruct;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        productRepository.save(new Product(null, "Product 1"));
        productRepository.save(new Product(null, "Product 2"));
        productRepository.save(new Product(null, "Product 3"));
        productRepository.save(new Product(null, "Product 4"));
        productRepository.save(new Product(null, "Product 5"));
    }
}
