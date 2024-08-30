package com.ecommerce.main;

import com.ecommerce.files.FileManager;
import com.ecommerce.model.*;
import com.ecommerce.stote.Store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceApp {

    private static Store store = new Store();
    private static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        loadExistingData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
        	 System.out.println("Store Management System");
             System.out.println("1. Add Product");
             System.out.println("2. Add Customer");
             System.out.println("3. Place Order");
             System.out.println("4. View Products");
             System.out.println("5. View Customers");
             System.out.println("6. View Orders");
             System.out.println("7. Remove Product");
             System.out.println("8. Remove Customer");
             System.out.println("9. Remove Order");
             System.out.println("10. Save Data");
             System.out.println("11. Exit");
             System.out.print("Choose an option: ");
             int choice = scanner.nextInt();
             scanner.nextLine(); // Consume newline

            switch (choice) {
            case 1:
                addProduct(scanner);
                break;
            case 2:
                addCustomer(scanner);
                break;
            case 3:
                placeOrder(scanner);
                break;
            case 4:
                viewProducts();
                break;
            case 5:
                viewCustomers();
                break;
            case 6:
                viewOrders();
                break;
            case 7:
                removeProduct(scanner);
                break;
            case 8:
                removeCustomer(scanner);
                break;
            case 9:
                removeOrder(scanner);
                break;
              case 10:
                    saveData();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadExistingData() {
        try {
            List<Product> products = fileManager.loadProducts();
            List<Customer> customers = fileManager.loadCustomers();
            List<Order> orders = fileManager.loadOrders();

            products.forEach(store::addProduct);
            customers.forEach(store::addCustomer);
            orders.forEach(store::addOrder);

            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveData() {
        try {
            fileManager.saveProducts(store.getProducts());
            fileManager.saveCustomers(store.getCustomers());
            fileManager.saveOrders(store.getOrders());
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = new Product(productId, name, price, quantity);
        store.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(customerId, name, email);
        store.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = store.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter product IDs (comma separated): ");
        String[] productIds = scanner.nextLine().split(",");

        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product product = store.findProductById(productId.trim());
            if (product != null) {
                products.add(product);
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        }

        if (products.isEmpty()) {
            System.out.println("No valid products found for the order.");
            return;
        }

        Order order = new Order(orderId, customer, products);
        store.addOrder(order);
        System.out.println("Order placed successfully.");
    }

    private static void viewProducts() {
    	 List<Product> products = store.getProducts();

         if (products.isEmpty()) {
             System.out.println("No products found in the store.");
         } else {
        	 System.out.println("Products in the store:");
             store.getProducts().forEach(System.out::println);
         }
    }

    private static void viewCustomers() {
        List<Customer> customers = store.getCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found in the store.");
        } else {
            System.out.println("Customers in the store:");
            customers.forEach(System.out::println);
        }
    }

    private static void viewOrders() {
    	 List<Order> orders = store.getOrders();

         if (orders.isEmpty()) {
             System.out.println("No orders found in the store.");
         } else {
        	 System.out.println("Orders placed:");
             store.getOrders().forEach(System.out::println);
         }

    }
    
    private static void removeProduct(Scanner scanner) {
        System.out.print("Enter Product ID to remove: ");
        String productId = scanner.next();

        Product product = store.findProductById(productId);
        if (product != null) {
            store.removeProduct(productId);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void removeCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID to remove: ");
        String customerId = scanner.next();

        Customer customer = store.findCustomerById(customerId);
        if (customer != null) {
            store.removeCustomer(customerId);
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void removeOrder(Scanner scanner) {
        System.out.print("Enter Order ID to remove: ");
        String orderId = scanner.next();

        Order order = store.findOrderById(orderId);
        if (order != null) {
            store.removeOrder(orderId);
            System.out.println("Order removed successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

}
