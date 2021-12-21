package ru.aberezhnoy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.aberezhnoy.persist.ProductRepository;
import ru.aberezhnoy.persist.ProductRepositoryImpl;

import java.io.ObjectInputFilter;

public class Main {

    public static void main(String[] args) {
//        ProductRepository productRepository = new ProductRepositoryImpl();
//        ProductService productService = new ProductService(productRepository);

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CartService cartService = context.getBean("cartService", CartService.class);
        CartService cartService1 = context.getBean("cartService", CartService.class);
        CartService cartService2 = context.getBean("cartService", CartService.class);

        System.out.println("Product count" + cartService);
    }

}
