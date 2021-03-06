package ru.aberezhnoy;

import ru.aberezhnoy.persist.Product;
import ru.aberezhnoy.persist.ProductRepository;
import ru.aberezhnoy.persist.ProductRepositoryImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        ProductRepository productRepository = new ProductRepositoryImpl();
        productRepository.save(new Product(1L,"Product 1"));
        productRepository.save(new Product(2L,"Product 2"));
        productRepository.save(new Product(3L,"Product 3"));
        productRepository.save(new Product(4L,"Product 4"));
        productRepository.save(new Product(5L,"Product 5"));
        productRepository.save(new Product(6L,"Product 6"));

        sc.setAttribute("productRepository", productRepository);
    }
}
