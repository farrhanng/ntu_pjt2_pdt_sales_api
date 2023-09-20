package com.pdt_sales.pdt_sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.exception.ProductNotFoundException;
import com.pdt_sales.pdt_sales.repository.ProductRepository;
import com.pdt_sales.pdt_sales.repository.SalesRepository;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private SalesRepository salesRepository;

  // Create 1 Product
  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  // Get 1 Product
  @Override
  public Product getProduct(Long productKey) {
    return productRepository.findById(productKey).orElseThrow(() -> new ProductNotFoundException(productKey));
  }

  // Get All Products
  @Override
  public List<Product> getAllProducts() {
    List<Product> allProducts = productRepository.findAll();
    return allProducts;
  }

  // Update 1 Product
  @Override
  public Product updateProduct(Long productKey, Product product) {
    Product productToUpdate = productRepository.findById(productKey)
        .orElseThrow(() -> new ProductNotFoundException(productKey));

    // Update the fields
    productToUpdate.setProductName(product.getProductName());
    productToUpdate.setModelName(product.getModelName());
    productToUpdate.setProductDescription(product.getProductDescription());
    productToUpdate.setProductColor(product.getProductColor());
    productToUpdate.setProductSize(product.getProductSize());
    productToUpdate.setProductCost(product.getProductCost());
    productToUpdate.setProductPrice(product.getProductPrice());
    return productRepository.save(productToUpdate);
  }

  // Delete 1 Product
  @Override
  public void deleteProduct(Long productKey) {
    productRepository.deleteById(productKey);
  }

  @Override
  public Double getProductProfit(Long productKey) {
      // Fetch the Product entity by its key
      Product product = productRepository.findById(productKey).orElse(null);
      if (product == null) {
          return null;
      }

      // Fetch all Sales records for this product
      List<Sales> salesList = salesRepository.findByProductKey(productKey); // Assuming you have a method for this in SalesRepository

      // Calculate the total profit
      double totalProfit = 0.0;
      for (Sales sale : salesList) {
          totalProfit += (product.getProductPrice() - product.getProductCost()) * sale.getOrderQuantity();
      }

      return totalProfit;
  }

  // // Search
  // @Override
  // public List<Product> searchProducts(String productName) {
  // List<Product> foundProducts =
  // productRepository.findByProductName(productName);
  // return foundProducts;
  // }

}
