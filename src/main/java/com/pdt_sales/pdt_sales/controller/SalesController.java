package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import javax.validation.Valid;

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

  // CREATE
  @PostMapping("")
public ResponseEntity<Sales> createSales(@Valid @RequestBody Sales sales) {
    Sales newSales = salesService.recordSale(sales);
    return new ResponseEntity<>(newSales, HttpStatus.CREATED);
  }

  // READ ONE SALE BY ORDER NUMBER
  @GetMapping("/orderNumber/{orderNumber}")
  public ResponseEntity<List<Sales>> getSalesByOrderNumber(@PathVariable String orderNumber) {
    List<Sales> foundSales = salesService.getSalesByOrderNumber(orderNumber);
    return new ResponseEntity<>(foundSales, HttpStatus.OK);
  }

  // READ ALL SALES BY DATE RANGE
  @GetMapping("/dateRange")
  public ResponseEntity<List<Sales>> getSalesByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
    List<Sales> allSales = salesService.getSalesByDateRange(startDate, endDate);
    return new ResponseEntity<>(allSales, HttpStatus.OK);
  }

  // UPDATE
  @PutMapping("{saleId}")
  public ResponseEntity<Sales> updateSale(@PathVariable Long saleId, @RequestBody Sales sales) {
    Sales updatedSales = salesService.updateSale(saleId, sales);
    return new ResponseEntity<>(updatedSales, HttpStatus.OK);
  }

  // DELETE
  @DeleteMapping("{saleId}")
  public ResponseEntity<HttpStatus> deleteSale(@PathVariable Long saleId) {
    salesService.deleteSale(saleId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // READ SALES BY CUSTOMER KEY
  @GetMapping("/byCustomer/{customerKey}")
  public ResponseEntity<List<Sales>> getSalesByCustomerKey(@PathVariable Long customerKey) {
    List<Sales> salesForCustomer = salesService.getSalesByCustomerKey(customerKey);
    return new ResponseEntity<>(salesForCustomer, HttpStatus.OK);
  }

  // READ SALES BY PRODUCT KEY
  @GetMapping("/byProduct/{productKey}")
  public ResponseEntity<List<Sales>> getSalesByProductKey(@PathVariable Long productKey) {
    List<Sales> salesForProduct = salesService.getSalesByProductKey(productKey);
    return new ResponseEntity<>(salesForProduct, HttpStatus.OK);
  }
}
=======
package com.pdt_sales.pdt_sales.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @PreAuthorize("hasRole('ADMIN')") // Only users with 'ADMIN' role can access this endpoint
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
    public ResponseEntity<Map<String, Object>> getTotalBill(@PathVariable Long customerKey) {
        Map<String, Object> totalBillAndProducts = salesService.calculateTotalBill(customerKey);
        return new ResponseEntity<>(totalBillAndProducts, HttpStatus.OK);
    }

  // Update
  @PutMapping("{salesId}")
  @PreAuthorize("hasRole('ADMIN')") // Only users with 'ADMIN' role can access this endpoint
  public ResponseEntity<Sales> updateSales(@PathVariable Long salesId, @RequestBody Sales sales) {
    Sales updatedSales = salesService.updateSales(salesId, sales);
    return new ResponseEntity<>(updatedSales, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("{salesId}")
  @PreAuthorize("hasRole('ADMIN')") // Only users with 'ADMIN' role can access this endpoint
  public ResponseEntity<HttpStatus> deleteSales(@PathVariable Long salesId) {
    salesService.deleteSales(salesId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}

