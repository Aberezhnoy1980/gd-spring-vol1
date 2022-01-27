package ru.aberezhnoy.dao;

import ru.aberezhnoy.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductDaoImpl implements ProductDao {

    private EntityManagerFactory entityManagerFactory;

    public ProductDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Product> findAll () {
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
                em -> em.createQuery("delete from Product where id = :id")
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

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = entityManagerFactory.createEntityManager();
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
