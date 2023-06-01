package ru.aberezhnoy;
;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;


@Component
public class EntityManagerFactoryInit {

    private EntityManagerFactory factory;

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
}
