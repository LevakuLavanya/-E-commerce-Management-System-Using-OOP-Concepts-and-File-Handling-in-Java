package com.ecommerce.model;
import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private String customerId;
    private String name;
    private String email;
    private Cart cart;

   
    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.cart = new Cart();
    }

   
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + "]";
    }
}



