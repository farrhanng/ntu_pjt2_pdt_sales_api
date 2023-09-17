package com.pdt_sales.pdt_sales.csvdataimporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.pdt_sales.pdt_sales.csvimportservice.ProductCsvImportService;
import com.pdt_sales.pdt_sales.csvimportservice.SalesCsvImportService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class CsvDataImporter {
    @Autowired
    private ProductCsvImportService productCsvImportService;

    @Autowired
    private SalesCsvImportService salesCsvImportService;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void importCsvDataOnStartup() {
        // Load CSV files from the classpath
        Resource productsCsvResource = applicationContext.getResource("classpath:products.csv");
        Resource salesCsvResource = applicationContext.getResource("classpath:sales.csv");

        try {
            // Get file paths from resources
            String productsCsvFilePath = productsCsvResource.getFile().getAbsolutePath();
            String salesCsvFilePath = salesCsvResource.getFile().getAbsolutePath();

            // Call your CSV import logic here
            productCsvImportService.importCsvData(productsCsvFilePath);
            salesCsvImportService.importCsvData(salesCsvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
