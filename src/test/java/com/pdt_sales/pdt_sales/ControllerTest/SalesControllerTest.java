package com.pdt_sales.pdt_sales.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdt_sales.pdt_sales.entity.Sales;
import com.pdt_sales.pdt_sales.service.SalesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SalesService salesService;

    private Sales testSale;

    @BeforeEach
    public void setUp() {
        // CREATE A SAMPLE SALE FOR TESTING
        testSale = new Sales();
        testSale = Sales.builder()
                .productKey(1L)
                .orderDate(LocalDate.now())
                .orderNumber("SAMPLE123")
                .customerKey(2L)
                .orderQuantity(10)
                .build();

    }

    // CREATE
    @Test
    public void testCreateSale() throws Exception {
        when(salesService.recordSale(any(Sales.class))).thenReturn(testSale);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testSale)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        verify(salesService, times(1)).recordSale(testSale);
    }

    // CREATE NEGATIVE TEST
    @Test
    public void testCreateSaleWithInvalidData() throws Exception {
        // Create a sale with invalid data
        Sales invalidSale = Sales.builder()
                .productKey(1L)
                .orderDate(LocalDate.now().minusDays(1)) // Violates @FutureOrPresent
                .orderNumber("") // Violates @NotBlank
                .customerKey(null) // Violates @NotNull
                .orderQuantity(0) // Violates @Min
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidSale)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    // READ (GET ONE BY ORDER NUMBER)
    @Test
    public void testGetSalesByOrderNumber() throws Exception {
        List<Sales> salesList = new ArrayList<>();
        salesList.add(testSale);

        when(salesService.getSalesByOrderNumber("TEST123")).thenReturn(salesList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/sales/orderNumber/TEST123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andReturn();

        verify(salesService, times(1)).getSalesByOrderNumber("TEST123");
    }

    // // READ NEGATIVE TEST (GET ONE BY ORDER NUMBER)
    // @Test
    // public void testGetNonExistingSaleByOrderNumber() throws Exception {
    // String nonExistingOrderNumber = "NONEXIST123";
    // when(salesService.getSalesByOrderNumber(nonExistingOrderNumber))
    // .thenThrow(new SalesNotFoundException("Sale not found"));

    // mockMvc.perform(MockMvcRequestBuilders
    // .get("/sales/orderNumber/" + nonExistingOrderNumber))
    // .andExpect(MockMvcResultMatchers.status().isNotFound())
    // .andReturn();
    // }

    // READ (GET ALL BY DATE RANGE)
    @Test
    public void testGetSalesByDateRange() throws Exception {
        List<Sales> salesList = new ArrayList<>();
        salesList.add(testSale);

        Date startDate = new Date(); // Adjust as necessary.
        Date endDate = new Date(); // Adjust as necessary.

        when(salesService.getSalesByDateRange(startDate, endDate)).thenReturn(salesList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/sales/dateRange?startDate=" + startDate + "&endDate=" + endDate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andReturn();

        verify(salesService, times(1)).getSalesByDateRange(startDate, endDate);
    }

    // UPDATE
    @Test
    public void testUpdateSale() throws Exception {
        when(salesService.updateSale(1L, testSale)).thenReturn(testSale);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/sales/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testSale)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(salesService, times(1)).updateSale(1L, testSale);
    }

    // // UPDATE NEGATIVE TEST
    // @Test
    // public void testUpdateNonExistingSale() throws Exception {
    // Long nonExistingSaleId = 999L; // Assume this ID doesn't exist.
    // when(salesService.updateSale(nonExistingSaleId, testSale))
    // .thenThrow(new SalesNotFoundException("Sale not found"));

    // mockMvc.perform(MockMvcRequestBuilders
    // .put("/sales/" + nonExistingSaleId)
    // .contentType(MediaType.APPLICATION_JSON)
    // .content(objectMapper.writeValueAsString(testSale)))
    // .andExpect(MockMvcResultMatchers.status().isNotFound())
    // .andReturn();
    // }

    // DELETE
    @Test
    public void testDeleteSale() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/sales/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        verify(salesService, times(1)).deleteSale(1L);
    }

    // SEARCH BY CUSTOMER KEY
    @Test
    public void testGetSalesByCustomerKey() throws Exception {
        List<Sales> salesList = new ArrayList<>();
        salesList.add(testSale);

        when(salesService.getSalesByCustomerKey(1L)).thenReturn(salesList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/sales/byCustomer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andReturn();

        verify(salesService, times(1)).getSalesByCustomerKey(1L);
    }

    // SEARCH BY PRODUCT KEY
    @Test
    public void testGetSalesByProductKey() throws Exception {
        List<Sales> salesList = new ArrayList<>();
        salesList.add(testSale);

        when(salesService.getSalesByProductKey(1L)).thenReturn(salesList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/sales/byProduct/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andReturn();

        verify(salesService, times(1)).getSalesByProductKey(1L);
    }
}
