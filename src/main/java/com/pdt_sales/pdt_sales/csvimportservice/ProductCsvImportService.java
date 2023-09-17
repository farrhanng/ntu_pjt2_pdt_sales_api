package com.pdt_sales.pdt_sales.csvimportservice;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;

@Service
public class ProductCsvImportService {

    @Autowired
    private ProductRepository productRepository;

    public void importCsvData(String csvFilePath) {
        try (Reader reader = new FileReader(csvFilePath);
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                Product product = Product.builder()
                        .productKey(Long.parseLong(line[0])) 
                        .productName(line[1])
                        .modelName(line[2])
                        .productDescription(line[3])
                        .productColor(line[4])
                        .productSize(line[5])
                        .productCost(Double.parseDouble(line[6]))
                        .productPrice(Double.parseDouble(line[7]))
                        .build();

                productRepository.save(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
