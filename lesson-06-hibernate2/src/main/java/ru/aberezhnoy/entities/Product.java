package ru.aberezhnoy.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "customers_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customerList;

    @OneToMany(
            mappedBy = "productId",
            orphanRemoval = true
    )
    private List<OrderList> orderList;

    public Product() {
    }

    public Product(Long id, String title, BigDecimal price, List<Customer> customerList, List<OrderList> orderList) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.customerList = customerList;
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, price = %s]", id, title, price);
    }

}
