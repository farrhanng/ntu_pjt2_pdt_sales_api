package com.pdt_sales.pdt_sales.service;

import java.util.List;

import com.pdt_sales.pdt_sales.entity.Sales;

public interface SalesService {

    Sales saveSales(Sales sales);

    Sales getSales(Long id);

    List<Sales> getAllSales();

    Sales updateSales(Long id, Sales sales);

    void deleteSales(Long id);

}
