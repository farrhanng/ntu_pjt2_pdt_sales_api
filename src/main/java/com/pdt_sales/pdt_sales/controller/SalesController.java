package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.service.SalesService;
import com.pdt_sales.pdt_sales.repository.SalesRepository;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SalesController {

  private SalesService salesService;

  // Create 1 sales record
  @PostMapping("")
  public ResponseEntity<Sales> createSales(@RequestBody Sales sales) {
    Sales newSales = salesService.saveSales(sales);
    return new ResponseEntity<>(newSales, HttpStatus.CREATED);
  }

  // Get all sales records
  @GetMapping("")
  public ResponseEntity<List<Sales>> getAllSales() {
    List<Sales> allSales = salesService.getAllSales();
    return new ResponseEntity<>(allSales, HttpStatus.OK);
  }

  // Get 1 sales record
  @GetMapping("{salesId}")
  public ResponseEntity<Sales> getSales(@PathVariable Long salesId) {
    Sales foundSales = salesService.getSales(salesId);
    return new ResponseEntity<>(foundSales, HttpStatus.OK);
  }

  @GetMapping("/totalBill/{customerKey}")
  public ResponseEntity<String> getTotalBill(@PathVariable Long customerKey) {
      Double totalBill = salesService.calculateTotalBill(customerKey);
      String response = String.format("The total bill for Customer ID %d is $%.2f", customerKey, totalBill);
      return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // Update
  @PutMapping("{salesId}")
  public ResponseEntity<Sales> updateSales(@PathVariable Long salesId, @RequestBody Sales sales) {
    Sales updatedSales = salesService.updateSales(salesId, sales);
    return new ResponseEntity<>(updatedSales, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("{salesId}")
  public ResponseEntity<HttpStatus> deleteSales(@PathVariable Long salesId) {
    salesService.deleteSales(salesId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
