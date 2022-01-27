package ru.aberezhnoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aberezhnoy.dao.CustomerDao;
import ru.aberezhnoy.dao.OrderListDao;
import ru.aberezhnoy.dao.ProductDao;
import ru.aberezhnoy.entities.Customer;
import ru.aberezhnoy.entities.Product;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerProductsService {

    private CustomerDao customerDao;
    private ProductDao productDao;
    private OrderListDao orderListDao;

    @Autowired
    public CustomerProductsService(CustomerDao customerDao, ProductDao productDao, OrderListDao orderListDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.orderListDao = orderListDao;
    }

    public CustomerProductsService() {
    }

    public List<Product> findProductsByCustomerId(Long customerId) {
        return productDao.findProductsByCustomerId(customerId);
    }

    public List<Customer> findCustomersByProductId(Long productId) {
        return customerDao.findCustomersByProductId(productId);
    }

    public Optional<Customer> findCustomerById(Long id) {
        return customerDao.findById(id);
    }

    public Optional<Product> findProductById(Long id) {
        return productDao.findById(id);
    }

    public void getPriceByCustomerAndProductId (Long customerId, Long productId){
        System.out.println("Данный покупатель (" + customerDao.findById(customerId).toString() +
                ") приобретал указанный товар (" + productDao.findById(productId).toString() + ") в заказах №№" +
                orderListDao.findOrderByCustomerAndOrderId(customerId,productId) + " по ценам: " +
                orderListDao.findPriceByCustomerAndOrderId(customerId,productId) + " соответственно.");
    }

    public void findOrders(){
        System.out.println("Полный список заказов: " + orderListDao.findAll());
    }
}
