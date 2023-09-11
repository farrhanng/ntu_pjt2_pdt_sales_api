package com.pdt_sales.pdt_sales.exception;

public class SalesNotFoundException extends RuntimeException {
  public SalesNotFoundException(Long id) {
    super("Could not find interaction " + id);
  }

}
