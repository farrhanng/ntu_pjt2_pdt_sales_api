package com.pdt_sales.pdt_sales.dataloader;

import org.springframework.stereotype.Component;

import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.List;

// @Component
public class ProductDataLoader {

    private ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void loadData() {
        // Clear the product table
        productRepository.deleteAll();

        // Load data for the first 2 rows
        List<Product> productList = List.of(
                Product.builder()
                        .productKey(214L) // Manually assign productKey
                        .productName("Sport-100 Helmet, Red")
                        .modelName("Sport-100")
                        .productDescription("Universal fit, well-vented, lightweight, snap-on visor.")
                        .productColor("Red")
                        .productSize("Universal")
                        .productCost(13.0863)
                        .productPrice(34.99)
                        .build(),
                Product.builder()
                        .productKey(215L) // Manually assign productKey
                        .productName("Sport-100 Helmet, Black")
                        .modelName("Sport-100")
                        .productDescription("Universal fit, well-vented, lightweight, snap-on visor.")
                        .productColor("Black")
                        .productSize("Universal")
                        .productCost(12.0278)
                        .productPrice(33.6442)
                        .build()

        );

        // Save the products to the database
        productRepository.saveAll(productList);
    }

}
