package ru.aberezhnoy.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Service;
import ru.aberezhnoy.service.dto.LineItem;
import ru.aberezhnoy.service.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CartServiceImpl implements CartService {
    private final List<LineItem> lineItems;

    public CartServiceImpl() {
        this.lineItems = new ArrayList<>();
    }

//    @JsonCreator
//    public CartServiceImpl(@JsonProperty("lineItems") List<LineItem> lineItems) {
//        this.lineItems = lineItems;
//    }

    @Override
    public List<LineItem> showAll() {
        return lineItems;
    }

    @Override
    public void addToCart(ProductDto productDto) {
        lineItems.add(new LineItem(productDto));
    }

    @Override
    public void removeFromCart(LineItem lineItem) {
        lineItems.remove(lineItem);
    }
}
