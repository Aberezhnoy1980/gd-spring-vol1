package ru.aberezhnoy.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.aberezhnoy.EntityManagerFactoryInit;
import ru.aberezhnoy.entities.Customer;
import ru.aberezhnoy.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class ProductDao implements Dao<Product, Long> {

    private EntityManagerFactoryInit factory;

    @Autowired
    public void setEntityManagerFactoryInit(EntityManagerFactoryInit factory) {
        this.factory = factory;
    }

    @Override
    public List<Product> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("select p from Product p", Product.class)
                        .getResultList()
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(Product.class, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        executeInTransaction(
                em -> em.createQuery("delete from Product where id =:id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    @Override
    public Product saveOrUpdate(Product product) {
        executeInTransaction(em -> {
            if (product.getId() == null) {
                em.persist(product);
            } else {
                em.merge(product);
            }
        });
        return product;
    }

    public List<Product> findProductsByCustomerId(Long customerId) {
        {
            return executeForEntityManager(
                    em -> {
                        Customer customer = em.find(Customer.class, customerId);
                        return customer.getProductList();
                    }
            );
        }
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = factory.getFactory().createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = factory.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
