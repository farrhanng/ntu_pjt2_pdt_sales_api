package com.pdt_sales.pdt_sales.service;

import java.util.List;
import java.util.Date;
import com.pdt_sales.pdt_sales.entity.Sales;

public interface SalesService {
    // Method to record a new sale
    Sales recordSale(Sales sale);

    // Method to retrieve sales by their OrderNumber
    List<Sales> getSalesByOrderNumber(String orderNumber);

    // Method to retrieve all sales between two dates
    List<Sales> getSalesByDateRange(Date startDate, Date endDate);

    // Method to update an existing sale (e.g., change quantity)
    Sales updateSale(Long saleId, Sales sale);

    // Method to delete a sale by its ID
    void deleteSale(Long saleId);

    // Method to retrieve sales for a particular customer
    List<Sales> getSalesByCustomerKey(Long customerKey);

    // Method to retrieve sales for a particular product
    List<Sales> getSalesByProductKey(Long productKey);
}