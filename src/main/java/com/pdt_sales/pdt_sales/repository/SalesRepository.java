package com.pdt_sales.pdt_sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdt_sales.pdt_sales.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    // You can add custom query methods here if needed
}