package com.pdt_sales.pdt_sales.csvdataimporter;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.pdt_sales.pdt_sales.csvimportservice.ProductCsvImportService;
import com.pdt_sales.pdt_sales.csvimportservice.SalesCsvImportService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class CsvDataImporter {

    // Inject the ResourceLoader so that we can import CSV files when using "docker-compose up"
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ProductCsvImportService productCsvImportService;

    @Autowired
    private SalesCsvImportService salesCsvImportService;

    @PostConstruct
    public void importCsvDataOnStartup() throws IOException, CsvValidationException {
        // Load CSV files from the classpath using resourceloader
        Resource productsCsvResource = resourceLoader.getResource("classpath:products.csv");
        Resource salesCsvResource = resourceLoader.getResource("classpath:sales.csv");

        try {
            // Get input streams from resources
            InputStream productsCsvStream = productsCsvResource.getInputStream();
            InputStream salesCsvStream = salesCsvResource.getInputStream();

            // Call your CSV import logic here with input streams
            productCsvImportService.importCsvData(productsCsvStream);
            salesCsvImportService.importCsvData(salesCsvStream);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace(); // Handle the exceptions appropriately
        }
    }
}


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import org.springframework.core.io.Resource;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.pdt_sales.pdt_sales.csvimportservice.ProductCsvImportService;
// import com.pdt_sales.pdt_sales.csvimportservice.SalesCsvImportService;
// import com.pdt_sales.pdt_sales.entity.Product; // Import your entity classes
// import com.pdt_sales.pdt_sales.entity.Sales;
// import com.pdt_sales.pdt_sales.repository.ProductRepository; // Import your repositories
// import com.pdt_sales.pdt_sales.repository.SalesRepository;

// import javax.annotation.PostConstruct;
// import java.io.IOException;
// import java.util.List;

// @Service
// public class CsvDataImporter {
//     @Autowired
//     private ProductCsvImportService productCsvImportService;

//     @Autowired
//     private SalesCsvImportService salesCsvImportService;

//     @Autowired
//     private ApplicationContext applicationContext;

//     @Autowired
//     private ProductRepository productRepository; // Inject your repositories
    
//     @Autowired
//     private SalesRepository salesRepository;

//     @PostConstruct
//     @Transactional // Use a transaction for the import process
//     public void importCsvDataOnStartup() {
//         // Load CSV files from the classpath
//         Resource productsCsvResource = applicationContext.getResource("classpath:products.csv");
//         Resource salesCsvResource = applicationContext.getResource("classpath:sales.csv");

//         try {
//             // Get file paths from resources
//             String productsCsvFilePath = productsCsvResource.getFile().getAbsolutePath();
//             String salesCsvFilePath = salesCsvResource.getFile().getAbsolutePath();

//             // Import CSV data into entities
//             List<Product> products = productCsvImportService.importCsvData(productsCsvFilePath);
//             List<Sales> sales = salesCsvImportService.importCsvData(salesCsvFilePath);

//             // Save entities to the database
//             productRepository.saveAll(products);
//             salesRepository.saveAll(sales);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
