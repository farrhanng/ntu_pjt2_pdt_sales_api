package com.pdt_sales.pdt_sales.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdt_sales.pdt_sales.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // You can perform any setup needed for your tests here.
    }

    @DisplayName("Post 1 Product")
    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void createProductTest() throws Exception {
        // Create a Product object using the builder
        Product product = Product.builder()
                .productKey(111L)
                .productName("Product 1")
                .modelName("Model 1")
                .productDescription("Description for Product 1")
                .productColor("Red")
                .productSize("Small")
                .productCost(10.0)
                .productPrice(20.0)
                .build();

        // Convert the Product object to JSON
        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productKey").value(111L)) // Adjust this assertion based on your response
                                                                 // structure
                .andExpect(jsonPath("$.productName").value("Product 1"))
                .andExpect(jsonPath("$.modelName").value("Model 1"))
                .andExpect(jsonPath("$.productDescription").value("Description for Product 1"))
                .andExpect(jsonPath("$.productColor").value("Red"))
                .andExpect(jsonPath("$.productSize").value("Small"))
                .andExpect(jsonPath("$.productCost").value(10.0))
                .andExpect(jsonPath("$.productPrice").value(20.0));
    }

    @DisplayName("Get All Products")
    @Test
    public void getAllProductsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Assuming your response is a JSON array of products
    }

    @Test
    public void getProductByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{productKey}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productKey").value(1)); // Adjust this assertion based on your response structure
    }

    @Test
    public void updateProductTest() throws Exception {
        // Create a Product object with updated data
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setModelName("Updated Model");
        // Set other fields to update

        // Convert the updated Product object to JSON
        String updatedProductJson = objectMapper.writeValueAsString(updatedProduct);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{productKey}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Updated Product")); // Adjust this assertion based on your
                                                                                // response structure
    }

    @Test
    public void deleteProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{productKey}", 1L))
                .andExpect(status().isNoContent());
    }
}
