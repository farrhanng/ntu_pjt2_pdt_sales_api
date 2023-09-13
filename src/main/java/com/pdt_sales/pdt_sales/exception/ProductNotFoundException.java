package com.pdt_sales.pdt_sales.exception;

public class ProductNotFoundException extends RuntimeException {
public ProductNotFoundException(Long id) {
    super("Could not find customer " + id);
  }

}