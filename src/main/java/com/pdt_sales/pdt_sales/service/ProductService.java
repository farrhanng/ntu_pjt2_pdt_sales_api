package com.pdt_sales.pdt_sales.service;

import java.util.List;

import com.pdt_sales.pdt_sales.entity.Product;

// Define the ProductService interface
public interface ProductService {

    // Method to create a new product
    Product createProduct(Product product);

    // Method to retrieve a product by their ProductKey
    Product getProduct(Long productKey);

    // Method to retrieve a list of all products
    List<Product> getAllProducts();

    // Method to update an existing product
    Product updateProduct(Long productKey, Product product);

    // Method to delete a product by their ProductKey
    void deleteProduct(Long productKey);

    // // Method to search for products by their name
    // List<Product> searchProducts(String productName);



}
