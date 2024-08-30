package com.ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    public void removeProduct(String productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
    }

    public List<Product> getProducts() {
        return products;
    }
    public double calculateTotalAmount() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart: \n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Total Amount: ").append(calculateTotalAmount());
        return sb.toString();
    }
}
