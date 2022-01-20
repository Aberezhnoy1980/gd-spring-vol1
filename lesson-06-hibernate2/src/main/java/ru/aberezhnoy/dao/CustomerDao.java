package ru.aberezhnoy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.aberezhnoy.EntityManagerFactoryInit;
import ru.aberezhnoy.entities.Customer;
import ru.aberezhnoy.entities.Product;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class CustomerDao implements Dao<Customer, Long> {

    private EntityManagerFactoryInit factory;

    @Autowired
    private void setEntityManagerFactoryInit(EntityManagerFactoryInit factory) {
        this.factory = factory;
    }

    @Override
    public List<Customer> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("select c from Customer c", Customer.class)
                        .getResultList()
        );
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(Customer.class, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        executeInTransaction(
                em -> em.createQuery("delete from Product where id = :id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        executeInTransaction(em -> {
            if (customer.getId() == null) {
                em.persist(customer);
            } else {
                em.merge(customer);
            }
        });
        return customer;
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

    public List<Customer> findCustomersByProductId(Long productId) {
            return executeForEntityManager(
                    em -> {
                        Product product = em.find(Product.class, productId);
                        return product.getCustomerList();
                    }
            );
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
