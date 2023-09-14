package com.pdt_sales.pdt_sales.controller;

import java.util.List;
import java.util.Date;

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
    Sales newSales = salesService.recordSale(sales);
    return new ResponseEntity<>(newSales, HttpStatus.CREATED);
  }

  // Read All Sales By Date Range
  @GetMapping("/dateRange")
  public ResponseEntity<List<Sales>> getSalesByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
    List<Sales> allSales = salesService.getSalesByDateRange(startDate, endDate);
    return new ResponseEntity<>(allSales, HttpStatus.OK);
  }

  // Read One Sale by Order Number
  @GetMapping("/orderNumber/{orderNumber}")
  public ResponseEntity<List<Sales>> getSalesByOrderNumber(@PathVariable String orderNumber) {
    List<Sales> foundSales = salesService.getSalesByOrderNumber(orderNumber);
    return new ResponseEntity<>(foundSales, HttpStatus.OK);
  }

  // Update
  @PutMapping("{id}")
  public ResponseEntity<Sales> updateSale(@PathVariable Long id, Sales sales) {
    Sales updatedSales = salesService.updateSale(id, sales);
    return new ResponseEntity<>(updatedSales, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteSale(@PathVariable Long id) {
    salesService.deleteSale(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
