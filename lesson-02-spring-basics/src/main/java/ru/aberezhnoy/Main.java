package ru.aberezhnoy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        context.getBean("cart", Cart.class).createCart(); // метод обработки корзины в отдельном классе

//        context.getBean("cartService", CartService.class).createCart(); // обработка класса в сервисе

//        CartService cartService = context.getBean("cartService", CartService.class); объявление через переменную
//        cartService.createCart();
    }
}
