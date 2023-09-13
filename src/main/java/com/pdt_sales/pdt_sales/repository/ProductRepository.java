package com.pdt_sales.pdt_sales.repository;

import com.pdt_sales.pdt_sales.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductSKU(String sku);
}
