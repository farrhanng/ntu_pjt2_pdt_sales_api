package com.pdt_sales.pdt_sales.csvimportservice;

// import com.opencsv.CSVReader;
// import com.opencsv.CSVReaderBuilder;
// import com.pdt_sales.pdt_sales.entity.Sales;
// import com.pdt_sales.pdt_sales.repository.SalesRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.io.FileReader;
// import java.io.Reader;
// import java.time.LocalDate;

// @Service
// public class SalesCsvImportService {
//     @Autowired
//     private SalesRepository salesRepository;

//     public void importCsvData(String csvFilePath) {
//         try (Reader reader = new FileReader(csvFilePath);
//                 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
//             String[] line;
//             while ((line = csvReader.readNext()) != null) {
//                 Sales sales = Sales.builder()
//                         .productKey(Long.parseLong(line[0]))
//                         .orderDate(LocalDate.parse(line[1])) // Ensure that the date format matches your CSV
//                         .orderNumber(line[2])
//                         .customerKey(Long.parseLong(line[3]))
//                         .orderQuantity(Integer.parseInt(line[4]))
//                         .build();

//                 salesRepository.save(sales);
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException; // Import CsvValidationException
import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class SalesCsvImportService {

    @Autowired
    private SalesRepository salesRepository;

    public void importCsvData(InputStream csvInputStream) throws IOException, CsvValidationException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvInputStream, StandardCharsets.UTF_8));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                Sales sales = Sales.builder()
                        .productKey(Long.parseLong(line[0]))
                        .orderDate(LocalDate.parse(line[1])) // Ensure that the date format matches your CSV
                        .orderNumber(line[2])
                        .customerKey(Long.parseLong(line[3]))
                        .orderQuantity(Integer.parseInt(line[4]))
                        .build();

                salesRepository.save(sales);
            }
        }
    }
}

