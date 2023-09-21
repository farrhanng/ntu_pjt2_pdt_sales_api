package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.service.ProductService;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    // Create 1 product
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')") // Only users with 'ADMIN' role can access this endpoint
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    // Get all products
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // Get 1 product
    @GetMapping("{productKey}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productKey) {
        return new ResponseEntity<>(productService.getProduct(productKey), HttpStatus.OK);
    }

    // Update 1 product
    @PutMapping("{productKey}")
    @PreAuthorize("hasRole('ADMIN')") // Only users with 'ADMIN' role can access this endpoint
    public ResponseEntity<Product> updateProduct(@PathVariable Long productKey, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(productKey, product), HttpStatus.OK);
    }

    // Delete 1 product
    @DeleteMapping("{productKey}")
    @PreAuthorize("hasRole('ADMIN')") // Only users with 'ADMIN' role can access this endpoint
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long productKey) {
        productService.deleteProduct(productKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Get Profit for 1 product
    @GetMapping("{productKey}/profit")
    public ResponseEntity<String> getProductProfit(@PathVariable Long productKey) {
    Double profit = productService.getProductProfit(productKey);
    if (profit != null) {
        String formattedProfit = String.format("%.2f", profit);
        String responseMessage = "The profit for Product " + productKey + " is $" + formattedProfit;
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
    }

    // // SEARCH BY SKU
    // @GetMapping("/search")
    // public ResponseEntity<List<Product>> searchProductsBySKU(@RequestParam String
    // sku) {
    // return new ResponseEntity<>(productService.searchProductsBySKU(sku),
    // HttpStatus.OK);
    // }

    // // SEARCH BY NAME
    // @GetMapping("/searchByName")
    // public ResponseEntity<List<Product>> searchProductsByName(@RequestParam
    // String name) {
    // return new ResponseEntity<>(productService.searchProductsByName(name),
    // HttpStatus.OK);
    // }
}