package ru.aberezhnoy.service;

import org.springframework.stereotype.Service;
import ru.aberezhnoy.persist.Product;
import ru.aberezhnoy.service.dto.LineItem;
import ru.aberezhnoy.service.dto.ProductDto;

import java.util.List;

@Service
public interface CartService {
    List<LineItem> showAll();

    void addToCart(ProductDto productDto);

    void removeFromCart(LineItem lineItem);
}
