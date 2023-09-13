package com.pdt_sales.pdt_sales.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "sales")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productKey")
public class Sales {
    // SalesId Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalesId") // Primary key for the Sales table
    private Long salesId;

    // ProductKey Column (Foreign Key)
    @Column(name = "ProductKey")
    private Long productKey; // Foreign key

    // OrderDate Column
    @Column(name = "OrderDate")
    @NotNull(message = "Order date is mandatory")
    @FutureOrPresent(message = "Order date should be in the present or future")
    private LocalDate orderDate; // Use LocalDate for date values

    // OrderNumber Column
    @Column(name = "OrderNumber")
    @NotBlank(message = "Order number is mandatory")
    private String orderNumber;

    // CustomerKey Column
    @Column(name = "CustomerKey")
    @NotNull(message = "Customer key is mandatory")
    private Long customerKey;

    // OrderQuantity Column
    @Column(name = "OrderQuantity")
    @NotNull(message = "Order quantity is mandatory")
    @Min(value = 1, message = "Order quantity should be at least 1")
    private int orderQuantity;

    // Lombok Builder: https://devwithus.com/lombok-builder-annotation/
    @Builder
    public Sales(Long productKey, LocalDate orderDate, String orderNumber, Long customerKey, int orderQuantity) {
        this.productKey = productKey;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
        this.customerKey = customerKey;
        this.orderQuantity = orderQuantity;
    }

}
