package com.pdt_sales.pdt_sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdt_sales.pdt_sales.entity.Sales;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {

    List<Sales> findByCustomerKey(Long customerKey);
    // You can add custom query methods here if needed

    List<Sales> findByProductKey(Long productKey);
}