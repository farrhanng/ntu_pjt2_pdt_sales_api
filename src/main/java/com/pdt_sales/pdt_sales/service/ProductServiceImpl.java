package com.pdt_sales.pdt_sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.exception.ProductNotFoundException;
import com.pdt_sales.pdt_sales.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;

  // Create
  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  // Get One
  @Override
  public Product getProduct(Long productKey) {
    return productRepository.findById(productKey).orElseThrow(() -> new ProductNotFoundException(productKey));
  }

  // Get All
  @Override
  public List<Product> getAllProducts() {
    List<Product> allProducts = productRepository.findAll();
    return allProducts;
  }

  // Update
  @Override
  public Product updateProduct(Long productKey, Product product) {
    Product productToUpdate = productRepository.findById(productKey).orElseThrow(() -> new ProductNotFoundException(productKey));

    // Update the fields
    productToUpdate.setProductName(product.getProductName());
    productToUpdate.setProductSKU(product.getProductSKU());
    productToUpdate.setModelName(product.getModelName());
    productToUpdate.setProductDescription(product.getProductDescription());
    productToUpdate.setProductColor(product.getProductColor());
    productToUpdate.setProductSize(product.getProductSize());
    productToUpdate.setProductStyle(product.getProductStyle());
    productToUpdate.setProductCost(product.getProductCost());
    productToUpdate.setProductPrice(product.getProductPrice());
    return productRepository.save(productToUpdate);
  }

  // Delete
  @Override
  public void deleteProduct(Long productKey) {
    productRepository.deleteById(productKey);
  }

  // Search
  @Override
  public List<Product> searchProducts(String productName) {
    List<Product> foundProducts = productRepository.findByProductName(productName);
    return foundProducts;
  }
}
