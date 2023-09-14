package com.pdt_sales.pdt_sales.service;

import java.util.List;

import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.entity.Sales;

public interface ProductService {

  Product createProduct(Product product);

  Product getProduct(Long id);

  List<Product> getAllProducts();

  Product updateProduct(Long id, Product product);

  void deleteProduct(Long id);

  Sales addSalesToProduct(Long id, Sales sales);

  List<Product> searchProducts(String name);

}
