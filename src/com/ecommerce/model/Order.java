package com.ecommerce.model;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String orderId;
    private Customer customer;
    private List<Product> products;
    private double totalAmount;

   
    public Order(String orderId, Customer customer, List<Product> products) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.totalAmount = calculateTotalAmount();
    }

    
    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

  
    private double calculateTotalAmount() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order [orderId=" + orderId + ", customer=" + customer.getName() + ", totalAmount=" + totalAmount + "]\n");
        sb.append("Products in Order:\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        return sb.toString();
    }
}
