package ru.aberezhnoy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aberezhnoy.service.CartService;
import ru.aberezhnoy.service.dto.LineItem;
import ru.aberezhnoy.service.dto.ProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartResource {
    private final CartService cartService;

    @Autowired
    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<LineItem> showAll() {
        return cartService.showAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(@RequestBody ProductDto productDto) {
        cartService.addToCart(productDto);
    }

    @DeleteMapping
    public void removeFromCart(@RequestBody LineItem lineItem) {
        cartService.removeFromCart(lineItem);
    }
}
