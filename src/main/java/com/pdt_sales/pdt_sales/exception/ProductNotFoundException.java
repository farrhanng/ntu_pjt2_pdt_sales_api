package com.pdt_sales.pdt_sales.exception;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Long ProductKey) {
    super("Could not find product " + ProductKey);
  }

}