package com.pdt_sales.pdt_sales.service;

import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.repository.SalesRepository;

@Service
@AllArgsConstructor
public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;

    @Override
    public Sales recordSale(Sales sale) {
        return salesRepository.save(sale);
    }

    @Override
    public List<Sales> getSalesByOrderNumber(String orderNumber) {
        return salesRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<Sales> getSalesByDateRange(Date startDate, Date endDate) {
        return salesRepository.findByOrderDateBetween(startDate, endDate);
    }

    @Override
    public Sales updateSale(Long saleId, Sales sale) {
        // Fetch the existing sale record from the database using the saleId
        Sales saleToUpdate = salesRepository.findById(saleId).orElseThrow(() -> new SaleNotFoundException(saleId));
        
        // Update the OrderQuantity
        saleToUpdate.setOrderQuantity(sale.getOrderQuantity());
        
        // Update the ProductKey
        saleToUpdate.setProductKey(sale.getProductKey());
        
        // Update the OrderDate
        saleToUpdate.setOrderDate(sale.getOrderDate());
        
        // Update the OrderNumber
        saleToUpdate.setOrderNumber(sale.getOrderNumber());
        
        // Update the CustomerKey
        saleToUpdate.setCustomerKey(sale.getCustomerKey());
        
        // Save the updated sale record back to the database
        return salesRepository.save(saleToUpdate);
    }
    

    @Override
    public void deleteSale(Long saleId) {
        salesRepository.deleteById(saleId);
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
