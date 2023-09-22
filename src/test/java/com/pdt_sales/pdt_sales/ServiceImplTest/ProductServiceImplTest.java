package com.pdt_sales.pdt_sales.ServiceImplTest;

import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.exception.ProductNotFoundException;
import com.pdt_sales.pdt_sales.repository.ProductRepository;
import com.pdt_sales.pdt_sales.service.ProductServiceImpl;
// import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
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





    // @DisplayName("Update 1 Product")
    // @Test
    // public void updateProductTest() {
    
    //     // 1. SETUP
    //     // Create a new product object and set a productKey
    //     Product product = new Product();
    //     Long productKey = 1L;
        
    //     // Mock the findById method of the product repository to return the product object
    //     when(productRepository.findById(productKey)).thenReturn(Optional.of(product));
        
    //     // Mock the save method of the product repository to return the updated product object
    //     when(productRepository.save(product)).thenReturn(product);
        
    //     // 2. EXECUTE
    //     // Call the method that we want to test
    //     Product updatedProduct = productService.updateProduct(productKey, product);
        
    //     // 3. ASSERT
    //     // Compare the actual result with the expected result
    //     assertEquals(product, updatedProduct, "The updated product should be the same as the product object");
    // }


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
