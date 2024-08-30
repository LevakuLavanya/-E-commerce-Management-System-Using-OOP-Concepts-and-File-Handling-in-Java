package com.ecommerce.stote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;

public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Product> products;
    private List<Customer> customers;
    private List<Order> orders;
    public Store() {
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
    }

 
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
 
    public Product findProductById(String productId) {
        return products.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }
    public Customer findCustomerById(String customerId) {
        return customers.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }
    public List<Product> getProducts() {
        return products;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void removeCustomer(String customerId) {
        customers.removeIf(customer -> customer.getCustomerId().equals(customerId));
    }

    public void removeOrder(String orderId) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
    }

    public Order findOrderById(String orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }
}
