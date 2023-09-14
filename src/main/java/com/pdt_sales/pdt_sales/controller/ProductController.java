package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.entity.Sales;
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

  // READ (GET ALL)
  @GetMapping("")
  public ResponseEntity<List<Product>> getAllProducts() {
    return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
  }

  // READ (GET ONE)
  @GetMapping("{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
  }

  // UPDATE
  @PutMapping("{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
  }

  // DELETE
  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
    return new ResponseEntity<>(productService.searchProducts(name), HttpStatus.OK);
  }

  @PostMapping("{id}/sales")
  public ResponseEntity<Sales> addSalesToProduct(@PathVariable Long id,
      @Valid @RequestBody Sales sales) {
    return new ResponseEntity<>(productService.addSalesToProduct(id, sales), HttpStatus.CREATED);
  }

}
