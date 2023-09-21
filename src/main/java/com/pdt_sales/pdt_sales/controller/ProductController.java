package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.service.ProductService;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    // CREATE
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    // READ (GET ONE)
    @GetMapping("{productKey}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productKey) {
        return new ResponseEntity<>(productService.getProduct(productKey), HttpStatus.OK);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("{productKey}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productKey, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(productKey, product), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("{productKey}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long productKey) {
        productService.deleteProduct(productKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // SEARCH BY NAME
    @GetMapping("/searchByName")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String productName) {
        return new ResponseEntity<>(productService.searchProducts(productName), HttpStatus.OK);
    }
}
