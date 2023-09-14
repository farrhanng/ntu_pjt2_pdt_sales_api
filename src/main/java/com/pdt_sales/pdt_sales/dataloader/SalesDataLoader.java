package com.pdt_sales.pdt_sales.dataloader;

import org.springframework.stereotype.Component;

import com.pdt_sales.pdt_sales.entity.Sales; // Change this to the actual Sales entity class
import com.pdt_sales.pdt_sales.repository.SalesRepository; // Change this to the actual Sales repository class

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SalesDataLoader {

    private SalesRepository salesRepository;

    public SalesDataLoader(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @PostConstruct
    public void loadData() {
        // Clear the sales table
        salesRepository.deleteAll();

        List<Sales> salesList = List.of(
                Sales.builder()
                        .productKey(214)
                        .orderDate("01-01-22")
                        .orderNumber("SO61280")
                        .customerKey(11891)
                        .orderQuantity(1)
                        .build(),
                Sales.builder()
                        .productKey(214)
                        .orderDate("01-01-22")
                        .orderNumber("SO61285")
                        .customerKey(23791)
                        .orderQuantity(1)
                        .build(),
                Sales.builder()
                        .productKey(214)
                        .orderDate("01-01-22")
                        .orderNumber("SO61298")
                        .customerKey(18155)
                        .orderQuantity(1)
                        .build(),
                Sales.builder()
                        .productKey(215)
                        .orderDate("01-01-22")
                        .orderNumber("SO61269")
                        .customerKey(11792)
                        .orderQuantity(1)
                        .build(),
                Sales.builder()
                        .productKey(215)
                        .orderDate("01-01-22")
                        .orderNumber("SO61289")
                        .customerKey(23694)
                        .orderQuantity(1)
                        .build(),
                Sales.builder()
                        .productKey(215)
                        .orderDate("01-01-22")
                        .orderNumber("SO61307")
                        .customerKey(20260)
                        .orderQuantity(1)
                        .build());

        // Save the sales records to the database
        salesRepository.saveAll(salesList);
    }
}
