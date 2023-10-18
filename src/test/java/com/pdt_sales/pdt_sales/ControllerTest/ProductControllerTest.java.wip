package com.pdt_sales.pdt_sales.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pdt_sales.pdt_sales.entity.Product;
import com.pdt_sales.pdt_sales.service.ProductService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
  
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Add your ProductService mock here
    @Autowired
    private ProductService productService;

@Test
@DisplayName("Create Product")
public void createProductTest() throws Exception {
    // Step 1: Create a Product object
    Product product = new Product();
    product.setProductName("Sample Product");
    product.setModelName("Sample Model");
    product.setProductDescription("A sample product description.");
    product.setProductColor("Red");
    product.setProductSize("Medium");
    product.setProductCost(10.99);
    product.setProductPrice(19.99);

    // Step 2: Convert the Product object to JSON using ObjectMapper
    String newProductAsJSON = objectMapper.writeValueAsString(product);

    // Step 3: Build the request
    RequestBuilder request = MockMvcRequestBuilders.post("/products")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newProductAsJSON);

    // Step 4: Perform the request, get response, and assert
    mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.productKey").exists()) // Assuming the product key is generated
        .andExpect(jsonPath("$.productName").value("Sample Product")) // Validate other fields as needed
        .andExpect(jsonPath("$.model").value("Sample Model"))
        .andExpect(jsonPath("$.productDescription").value("A sample product description."))
        .andExpect(jsonPath("$.productColor").value("Red"))
        .andExpect(jsonPath("$.productSize").value("Medium"))
        .andExpect(jsonPath("$.productCost").value(10.99))
        .andExpect(jsonPath("$.productPrice").value(19.99))
        .andReturn();
}


@DisplayName("Get All Products")
    @Test
    public void getAllProductsTest() throws Exception {
        // Step 1: Build a GET request to /products
        RequestBuilder request = MockMvcRequestBuilders.get("/products");

        // Step 2: Perform the request, get the response, and assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            // Add more assertions as needed
            .andReturn();
    }



    // java.lang.IncompatibleClassChangeError: Class org.springframework.mock.web.MockHttpServletRequest does not implement the requested interface jakarta.servlet.http.HttpServletRequest
    // at org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors$SecurityContextRequestPostProcessorSupport$TestSecurityContextRepository.getContext(SecurityMockMvcRequestPostProcessors.java:800)
    // at org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors$TestSecurityContextHolderPostProcessor.postProcessRequest(SecurityMockMvcRequestPostProcessors.java:821)
    // at org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.postProcessRequest(MockHttpServletRequestBuilder.java:843)
    // at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:191)
    // at com.pdt_sales.pdt_sales.ControllerTest.ProductControllerTest.createProductTest(ProductControllerTest.java:55)
    // at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    // at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

}
