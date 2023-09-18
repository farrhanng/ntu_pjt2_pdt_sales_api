package com.pdt_sales.pdt_sales.service;

import java.util.List;
import java.util.Date;
import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.entity.Product;

public interface SalesService {
    // Method to save a new sales record
    Sales saveSales(Sales sales);

    // Method to retrieve sales by their OrderNumber
    // List<Sales> getSalesByOrderNumber(String orderNumber);

    // Method to retrieve 1 sales record by salesId
    Sales getSales(Long salesId);

    // Method to retrieve all sales between two dates
    // List<Sales> getSalesByDateRange(Date startDate, Date endDate);

    // Method to get all sales
    List<Sales> getAllSales();

    // Method to update an existing sale (e.g., change quantity)
    Sales updateSales(Long salesId, Sales sales);

    // Method to delete a sale record by its ID
    void deleteSales(Long salesId);

    // Method to retrieve sales for a particular customer
    // List<Sales> getSalesByCustomerKey(Long customerKey);

    // Method to retrieve sales for a particular product
    // List<Sales> getSalesByProductKey(Long productKey);

    // Method to calculate and set revenue for a list of sales
    // List<Sales> calculateAndSetRevenue(List<Sales> salesList, Long productKey);

}