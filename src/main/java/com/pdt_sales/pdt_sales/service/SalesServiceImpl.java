package com.pdt_sales.pdt_sales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.exception.SalesNotFoundException;
import com.pdt_sales.pdt_sales.repository.SalesRepository;
import com.pdt_sales.pdt_sales.response.CustomerBillResponse;
import com.pdt_sales.pdt_sales.service.ProductService;
import com.pdt_sales.pdt_sales.repository.SalesRepository;

@Service
@AllArgsConstructor
public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;
    private ProductService productService;

    // Create 1 sales record
    @Override
    public Sales saveSales(Sales sales) {
        return salesRepository.save(sales);
    }

    // Get 1 sales record
    @Override
    public Sales getSales(Long salesId) {
        return salesRepository.findById(salesId).orElseThrow(() -> new SalesNotFoundException(salesId));
    }

    // Get all sales records
    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    // Update 1 sales record
    @Override
    public Sales updateSales(Long salesId, Sales sales) {
        // Fetch the existing sale record from the database using the saleId
        Sales salesToUpdate = salesRepository.findById(salesId).orElseThrow(() -> new SalesNotFoundException(salesId));

        // Update the ProductKey
        salesToUpdate.setProductKey(sales.getProductKey());

        // Update the OrderDate
        salesToUpdate.setOrderDate(sales.getOrderDate());

        // Update the OrderNumber
        salesToUpdate.setOrderNumber(sales.getOrderNumber());

        // Update the CustomerKey
        salesToUpdate.setCustomerKey(sales.getCustomerKey());

        // Update the OrderQuantity
        salesToUpdate.setOrderQuantity(sales.getOrderQuantity());

        // Save the updated sale record back to the database
        return salesRepository.save(salesToUpdate);
    }

    // Delete 1 sales record
    @Override
    public void deleteSales(Long salesId) {
        salesRepository.deleteById(salesId);
    }

    @Override
    public Double calculateTotalBill(Long customerKey) {
        List<Sales> salesList = salesRepository.findByCustomerKey(customerKey);

        double totalBill = 0.0;

        for (Sales sale : salesList) {
            Product product = productService.getProduct(sale.getProductKey());
            totalBill += product.getProductPrice() * sale.getOrderQuantity();
        }

        return totalBill;
    }

    @Override
    public List<Sales> getSalesByCustomerKey(Long customerKey) {
    return salesRepository.findByCustomerKey(customerKey);
    }

    @Override
    public List<Sales> getSalesByProductKey(Long productKey) {
    return salesRepository.findByProductKey(productKey);
    }
}