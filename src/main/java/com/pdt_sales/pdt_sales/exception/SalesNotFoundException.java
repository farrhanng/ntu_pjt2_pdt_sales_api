package com.pdt_sales.pdt_sales.exception;

public class SalesNotFoundException extends RuntimeException {
    public SalesNotFoundException(Long salesId) {
        super("Could not find sales record " + salesId);
    }

}
