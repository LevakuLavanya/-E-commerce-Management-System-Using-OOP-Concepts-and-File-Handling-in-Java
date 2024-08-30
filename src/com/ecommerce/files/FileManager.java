package com.ecommerce.files;
import com.ecommerce.model.Product;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String PRODUCTS_FILE = "C:/Users/lavan/OneDrive/Desktop/Ecommerce project/productdata.txt";
    private static final String CUSTOMERS_FILE = "C:/Users/lavan/OneDrive/Desktop/Ecommerce project/customerdata.txt";
    private static final String ORDERS_FILE = "C:/Users/lavan/OneDrive/Desktop/Ecommerce project/orderdata.txt";

    public void saveProducts(List<Product> products) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE))) {
            oos.writeObject(products);
        }
        
    }

    @SuppressWarnings("unchecked")
    public List<Product> loadProducts() throws IOException, ClassNotFoundException {
        File file = new File(PRODUCTS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Product>) ois.readObject();
        }
    }

    public void saveCustomers(List<Customer> customers) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMERS_FILE))) {
            oos.writeObject(customers);
        }
    }
    @SuppressWarnings("unchecked")
    public List<Customer> loadCustomers() throws IOException, ClassNotFoundException {
        File file = new File(CUSTOMERS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Customer>) ois.readObject();
        }
    }

    public void saveOrders(List<Order> orders) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE))) {
            oos.writeObject(orders);
        }
    }
    @SuppressWarnings("unchecked")
    public List<Order> loadOrders() throws IOException, ClassNotFoundException {
        File file = new File(ORDERS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Order>) ois.readObject();
        }
    }
}
