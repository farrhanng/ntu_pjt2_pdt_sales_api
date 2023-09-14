package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.service.SalesService;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SalesController {

    private SalesService salesService;


  // Create
  @PostMapping("")
  public ResponseEntity<Sales> createSales(@RequestBody Sales sales) {
    Sales newSales = salesService.saveSales(sales);
    return new ResponseEntity<>(newSales, HttpStatus.CREATED);
  }

  // Read All
  @GetMapping("")
  public ResponseEntity<List<Sales>> getAllSales() {
    List<Sales> allSales = salesService.getAllSales();
    return new ResponseEntity<>(allSales, HttpStatus.OK);
  }

  // Read One
  @GetMapping("{id}")
  public ResponseEntity<Sales> getSales(@PathVariable Long id) {
    Sales foundSales = salesService.getSales(id);
    return new ResponseEntity<>(foundSales, HttpStatus.OK);
  }

  // Update
  @PutMapping("{id}")
  public ResponseEntity<Sales> updateSales(@PathVariable Long id, Sales sales) {
    Sales updatedSales = salesService.updateSales(id, sales);
    return new ResponseEntity<>(updatedSales, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteSales(@PathVariable Long id) {
    salesService.deleteSales(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
