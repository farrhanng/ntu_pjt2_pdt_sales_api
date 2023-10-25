package com.pdt_sales.pdt_sales.ServiceImplTest;

import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.exception.ProductNotFoundException;
import com.pdt_sales.pdt_sales.repository.ProductRepository;
import com.pdt_sales.pdt_sales.service.ProductServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:test-application.properties")
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Create 1 Product")
    @Test
    public void createProductTest() {
        // Arrange
        Product productToCreate = Product.builder()
                .productKey(111L)
                .productName("Product 1")
                .modelName("Model 1")
                .productDescription("Description for Product 1")
                .productColor("Red")
                .productSize("Small")
                .productCost(10.0)
                .productPrice(20.0)
                .build();

        when(productRepository.save(productToCreate)).thenReturn(productToCreate);

        // Act
        Product createdProduct = productService.createProduct(productToCreate);

        // Assert
        assertEquals(productToCreate, createdProduct, "Created product should match input product");
        verify(productRepository, times(1)).save(productToCreate);
    }

    @DisplayName("Get 1 Product by ProductKey")
    @Test
    public void getProductTest() {
        // Arrange
        Long productKey = 111L;
        Product product = Product.builder()
                .productKey(productKey)
                .productName("Product 1")
                .modelName("Model 1")
                .productDescription("Description for Product 1")
                .productColor("Red")
                .productSize("Small")
                .productCost(10.0)
                .productPrice(20.0)
                .build();

        when(productRepository.findById(productKey)).thenReturn(Optional.of(product));

        // Act
        Product retrievedProduct = productService.getProduct(productKey);

        // Assert
        assertEquals(product, retrievedProduct, "Retrieved product should match expected product");
    }

    @DisplayName("Get All Products")
    @Test
    public void getAllProductsTest() {

        // 1. SETUP
        // Create a list of product objects and add products directly
        java.util.List<Product> productList = new ArrayList<>();

        productList.add(Product.builder()
                .productKey(1L)
                .productName("Product 1")
                .modelName("Model 1")
                .productDescription("Description 1")
                .productColor("Color 1")
                .productSize("Size 1")
                .productCost(10.0)
                .productPrice(20.0)
                .build());

        productList.add(Product.builder()
                .productKey(2L)
                .productName("Product 2")
                .modelName("Model 2")
                .productDescription("Description 2")
                .productColor("Color 2")
                .productSize("Size 2")
                .productCost(15.0)
                .productPrice(25.0)
                .build());

        // Mock the findAll method of the product repository to return the productList
        when(productRepository.findAll()).thenReturn(productList);

        // 2. EXECUTE
        // Call the method that we want to test
        java.util.List<Product> retrievedProductList = productService.getAllProducts();

        // 3. ASSERT
        // Compare the actual result with the expected result
        assertEquals(productList, retrievedProductList,
                "The retrieved product list should be the same as the product list");
    }

    @DisplayName("Update 1 Product")
    @Test
    public void updateProductTest() {
        // 1. SETUP
        Long productKey = 1L;
        // Create a sample existing product
        Product existingProduct = Product.builder()
                .productKey(productKey)
                .productName("Existing Product")
                .modelName("Model for Existing")
                .productDescription("Description for Existing")
                .productColor("Blue")
                .productSize("Medium")
                .productCost(15.0)
                .productPrice(30.0)
                .build();

        // Create a sample updated product
        Product updatedProduct = Product.builder()
                .productKey(productKey)
                .productName("Updated Product Name")
                .modelName("Model for Update")
                .productDescription("Updated Description")
                .productColor("Red")
                .productSize("Small")
                .productCost(20.0)
                .productPrice(40.0)
                .build();

        // Mock the findById method of the product repository to return the existing
        // product
        when(productRepository.findById(productKey)).thenReturn(Optional.of(existingProduct));

        // Mock the save method of the product repository to return the updated product
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        // 2. EXECUTE
        // Call the method that we want to test
        Product result = productService.updateProduct(productKey, updatedProduct);

        // 3. ASSERT
        // Verify that the productRepository methods were called with the expected
        // arguments
        verify(productRepository, times(1)).findById(productKey);
        verify(productRepository, times(1)).save(existingProduct);

        // Verify that the result matches the updated product
        assertEquals(updatedProduct, result);
    }

    @DisplayName("Delete 1 Product")
    @Test
    public void deleteProductTest() {
        // 1. SETUP
        Long productKey = 1L;

        // Create a sample existing product
        Product existingProduct = Product.builder()
                .productKey(productKey)
                .productName("Existing Product")
                .modelName("Model for Existing")
                .productDescription("Description for Existing")
                .productColor("Blue")
                .productSize("Medium")
                .productCost(15.0)
                .productPrice(30.0)
                .build();

        // Mock the findById method of the product repository to return the existing
        // product
        when(productRepository.findById(productKey)).thenReturn(Optional.of(existingProduct));

        // 2. EXECUTE
        // Call the method that we want to test
        productService.deleteProduct(productKey);

        // 3. ASSERT
        // Verify that the productRepository's deleteById method was called with the
        // expected argument
        verify(productRepository, times(1)).deleteById(productKey);
    }

    @DisplayName("Get Product by Non-Existent ProductKey")
    @Test
    public void getProductNotFoundTest() {
        // Arrange
        Long nonExistentProductKey = 999L;
        when(productRepository.findById(nonExistentProductKey)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(nonExistentProductKey));
    }

}
