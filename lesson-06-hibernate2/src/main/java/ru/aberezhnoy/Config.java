package ru.aberezhnoy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.aberezhnoy.dao.CustomerDao;
import ru.aberezhnoy.dao.Dao;
import ru.aberezhnoy.dao.OrderListDao;
import ru.aberezhnoy.dao.ProductDao;
import ru.aberezhnoy.service.CustomerProductsService;

@ComponentScan("ru.aberezhnoy")
@Configuration
public class Config {

    @Bean
    public EntityManagerFactoryInit entityManagerFactoryInit() {
        return new EntityManagerFactoryInit();
    }

    @Bean
    public CustomerProductsService customerProductsService() {
        return new CustomerProductsService();
    }

    @Bean
    public Dao productDao() {
        return new ProductDao();
    }

    @Bean
    public Dao customerDao() {
        return new CustomerDao();
    }

    @Bean
    public Dao orderListDao() {
        return new OrderListDao();
    }
}
