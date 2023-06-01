package ru.aberezhnoy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.aberezhnoy.EntityManagerFactoryInit;
import ru.aberezhnoy.entities.OrderList;
import ru.aberezhnoy.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class OrderListDao implements Dao<OrderList, Long> {

    private EntityManagerFactoryInit factory;

    @Autowired
    private void setEntityManagerFactoryInit(EntityManagerFactoryInit factory) {
        this.factory = factory;
    }

    public List<Product> findProductsByCustomerId(Long customerId) {
        return executeForEntityManager(
                em -> em.createQuery(
                                "select p " +
                                        "from Customer c " +
                                        "join c.orderList ol " +
                                        "join ol.productId p " +
                                        "where c.id = :customerId")
                        .setParameter("customerId", customerId)
                        .getResultList()
        );
    }

    public List<Long> findOrderByCustomerAndOrderId(Long customerId, Long productId) {
        return executeForEntityManager(
                em -> em.createQuery("select o.id from OrderList o where o.customerId= :customerId and o.productId= :productId")
                        .setParameter("customerId", customerId)
                        .setParameter("productId", productId)
                        .getResultList()
        );
    }

    public List<Integer> findPriceByCustomerAndOrderId(Long customerId, Long productId) {
        return executeForEntityManager(
                em -> em.createQuery(
                                "select o.price from OrderList o where o.customerId= :customerId and o.productId= :productId")
                        .setParameter("customerId", customerId)
                        .setParameter("productId", productId)
                        .getResultList()
        );
    }


    @Override
    public List<OrderList> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("select c from OrderList c", OrderList.class)
                        .getResultList()
        );
    }

    @Override
    public Optional<OrderList> findById(Long order_id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(OrderList.class, order_id))
        );
    }

    @Override
    public void deleteById(Long id) {
        executeInTransaction(
                em -> em.createQuery("delete from OrderList where id = :id", OrderList.class)
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    @Override
    public OrderList saveOrUpdate(OrderList obj) {
        return null;
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
