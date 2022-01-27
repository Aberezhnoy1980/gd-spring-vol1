package ru.aberezhnoy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.aberezhnoy.service.CustomerProductsService;

public class SpringHibernateApplication {

    public static void main (String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        CustomerProductsService service = context.getBean(CustomerProductsService.class);

        service.findProductsByCustomerId(1L);
    }
}
