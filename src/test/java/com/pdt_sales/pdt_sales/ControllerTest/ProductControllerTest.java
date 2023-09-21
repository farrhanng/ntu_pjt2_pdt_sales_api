package com.pdt_sales.pdt_sales.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        // Create a sample product for testing
        testProduct = new Product("Test Product", "TEST123", "A test product for testing", "SampleColor", "SampleSize",
                10.99, 20.0);
    }

    // CREATE

    @Test
    public void testCreateProduct() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(testProduct);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testProduct)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productKey", is(1))) // Changed from $.id to $.productKey
                .andReturn();

        System.out.println("Test Result: " + result.getResponse().getContentAsString());

        verify(productService, times(1)).createProduct(testProduct);
    }

    // READ (GET ONE)
    @Test
    public void testGetProduct() throws Exception {
        when(productService.getProduct(1L)).thenReturn(testProduct);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productKey", is(1))) // Changed from $.id to $.productKey
                .andReturn();

        verify(productService, times(1)).getProduct(1L);
    }

    // READ (GET ALL)
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(testProduct);

        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productKey", is(1))) // Changed from $.id to
                                                                                     // $.productKey
                .andReturn();

        verify(productService, times(1)).getAllProducts();
    }

    // UPDATE
    @Test
    public void testUpdateProduct() throws Exception {
        when(productService.updateProduct(1L, testProduct)).thenReturn(testProduct);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productKey", is(1))) // Changed from $.id to $.productKey
                .andReturn();

        verify(productService, times(1)).updateProduct(1L, testProduct);
    }

    // DELETE
    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        verify(productService, times(1)).deleteProduct(1L);
    }

    // SEARCH BY NAME
    @Test
    public void testSearchProductsByName() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(testProduct);

        when(productService.searchProducts("Test Product")).thenReturn(productList); // Mocked the service call

        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/searchByName?productName=Test Product") // Changed from ?name to ?productName
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productKey", is(1))) // Changed from $.id to
                                                                                     // $.productKey
                .andReturn();

        verify(productService, times(1)).searchProducts("Test Product"); // Verify with the correct method argument
    }
}
