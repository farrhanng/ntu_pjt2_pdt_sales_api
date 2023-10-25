package com.pdt_sales.pdt_sales.ServiceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.repository.SalesRepository;
import com.pdt_sales.pdt_sales.service.ProductService;
import com.pdt_sales.pdt_sales.service.SalesServiceImpl;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:test-application.properties")
public class SalesServiceImptTest {

    @Mock
    private SalesRepository salesRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private SalesServiceImpl salesService;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Create 1 Sales Record")
    @Test
    public void createSalesTest() {
        // Arrange
        Sales salesToCreate = Sales.builder()
                .productKey(111L)
                .orderDate(LocalDate.now())
                .orderNumber("Order123")
                .customerKey(1L)
                .orderQuantity(5)
                .build();

        when(salesRepository.save(salesToCreate)).thenReturn(salesToCreate);

        // Act
        Sales createdSales = salesService.saveSales(salesToCreate);

        // Assert
        assertEquals(salesToCreate, createdSales, "Created sales record should match input sales record");
        verify(salesRepository, times(1)).save(salesToCreate);
    }

    @DisplayName("Get 1 Sales Record by SalesId")
    @Test
    public void getSalesTest() {
        // Arrange
        Long salesId = 123L;
        Sales sales = new Sales();
        sales.setSalesId(salesId);
        sales.setProductKey(111L);
        sales.setOrderDate(LocalDate.now());
        sales.setOrderNumber("Order123");
        sales.setCustomerKey(1L);
        sales.setOrderQuantity(5);

        when(salesRepository.findById(salesId)).thenReturn(Optional.of(sales));

        // Act
        Sales retrievedSales = salesService.getSales(salesId);

        // Assert
        assertEquals(sales, retrievedSales, "Retrieved sales record should match expected sales record");
    }

    @DisplayName("Update 1 Sales Record")
    @Test
    public void updateSalesTest() {
        // Arrange
        Long salesId = 1L;
        // Create a sample existing sales record
        Sales existingSales = new Sales();
        existingSales.setSalesId(salesId);
        existingSales.setProductKey(111L);
        existingSales.setOrderDate(LocalDate.now());
        existingSales.setOrderNumber("Order123");
        existingSales.setCustomerKey(1L);
        existingSales.setOrderQuantity(5);

        // Create a sample updated sales record
        Sales updatedSales = new Sales();
        updatedSales.setSalesId(salesId);
        updatedSales.setProductKey(222L);
        updatedSales.setOrderDate(LocalDate.now());
        updatedSales.setOrderNumber("UpdatedOrder");
        updatedSales.setCustomerKey(2L);
        updatedSales.setOrderQuantity(10);

        // Mock the findById method of the sales repository to return the existing sales
        // record
        when(salesRepository.findById(salesId)).thenReturn(Optional.of(existingSales));

        // Mock the save method of the sales repository to return the updated sales
        // record
        when(salesRepository.save(existingSales)).thenReturn(updatedSales);

        // 2. EXECUTE
        // Call the method that we want to test
        Sales result = salesService.updateSales(salesId, updatedSales);

        // 3. ASSERT
        // Verify that the salesRepository methods were called with the expected
        // arguments
        verify(salesRepository, times(1)).findById(salesId);
        verify(salesRepository, times(1)).save(existingSales);

        // Verify that the result matches the updated sales record
        assertEquals(updatedSales, result);
    }

    @DisplayName("Delete 1 Sales Record")
    @Test
    public void deleteSalesTest() {
        // 1. SETUP
        Long salesId = 1L;

        // Create a sample existing sales record
        Sales existingSales = new Sales();
        existingSales.setSalesId(salesId);
        existingSales.setProductKey(111L);
        existingSales.setOrderDate(LocalDate.now());
        existingSales.setOrderNumber("Order123");
        existingSales.setCustomerKey(1L);
        existingSales.setOrderQuantity(5);

        // Mock the findById method of the sales repository to return the existing sales
        // record
        when(salesRepository.findById(salesId)).thenReturn(Optional.of(existingSales));

        // 2. EXECUTE
        // Call the method that we want to test
        salesService.deleteSales(salesId);

        // 3. ASSERT
        // Verify that the salesRepository's deleteById method was called with the
        // expected argument
        verify(salesRepository, times(1)).deleteById(salesId);
    }

}
