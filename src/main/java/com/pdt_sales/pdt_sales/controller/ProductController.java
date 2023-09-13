package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Customer;
import com.pdt_sales.pdt_sales.entity.Interaction;
import com.pdt_sales.pdt_sales.service.CustomerService;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class ProductController {

  private CustomerService customerService;

  // CREATE
  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
    return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
  }

  // READ (GET ALL)
  @GetMapping("")
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
  }

  // READ (GET ONE)
  @GetMapping("{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
  }

  // UPDATE
  @PutMapping("{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.OK);

  }

  // DELETE
  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String firstName) {
    return new ResponseEntity<>(customerService.searchCustomers(firstName), HttpStatus.OK);
  }

  // NESTED ROUTE
  @PostMapping("{id}/interactions")
  public ResponseEntity<Interaction> addInteractionToCustomer(@PathVariable Long id,
      @Valid @RequestBody Interaction interaction) {
    return new ResponseEntity<>(customerService.addInteractionToCustomer(id, interaction), HttpStatus.CREATED);
  }

}
