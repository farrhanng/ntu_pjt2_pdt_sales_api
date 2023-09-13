package com.pdt_sales.pdt_sales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdt_sales.pdt_sales.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom query methods here if needed
}
