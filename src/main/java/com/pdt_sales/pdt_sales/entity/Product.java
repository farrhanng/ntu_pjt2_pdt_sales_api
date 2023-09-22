package com.pdt_sales.pdt_sales.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// @AllArgsConstructor // comment out because I'm manually assigning productKey
// for now...
@EqualsAndHashCode
@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productKey")
public class Product {
    // ProductKey Column (Primary Key)
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_key") // changed column name to match postgres
    private Long productKey;

    // ProductName Column
    @Column(name = "product_name") // changed column name to match postgres
    @NotBlank(message = "Product name is mandatory")
    @Size(max = 255, message = "Product name should be less than or equal to 255 characters")
    private String productName;

    // ModelName Column
    @Column(name = "model_name") // changed column name to match postgres
    @Size(max = 255, message = "Model name should be less than or equal to 255 characters")
    private String modelName;

    // ProductDescription Column
    @Column(name = "product_description") // changed column name to match postgres
    @Size(max = 1000, message = "Product description should be less than or equal to 1000 characters")
    private String productDescription;

    // ProductColor Column
    @Column(name = "product_color") // changed column name to match postgres
    @NotBlank(message = "Product color is mandatory")
    @Size(max = 255, message = "Product color should be less than or equal to 255 characters")
    private String productColor;

    // ProductSize Column
    @Column(name = "product_size") // changed column name to match postgres
    @NotBlank(message = "Product size is mandatory")
    @Size(max = 50, message = "Product size should be less than or equal to 50 characters")
    private String productSize;

    // ProductCost Column
    @Column(name = "product_cost") // changed column name to match postgres
    @NotNull(message = "Product cost is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product cost must be greater than 0")
    private double productCost;

    // ProductPrice Column
    @Column(name = "product_price") // changed column name to match postgres
    @NotNull(message = "Product price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than 0")
    private double productPrice;

    // Calculated Column for Margin
    @Formula("product_price - product_cost")
    private double margin;

    // One to Many relationship
    // 1 product can appear in many sales records
    @OneToMany(mappedBy = "productKey")
    private List<Sales> sales;

    @Builder
    public Product(Long productKey, String productName, String modelName, String productDescription,
            String productColor, String productSize, double productCost, double productPrice) {
        this.productKey = productKey;
        this.productName = productName;
        this.modelName = modelName;
        this.productDescription = productDescription;
        this.productColor = productColor;
        this.productSize = productSize;
        this.productCost = productCost;
        this.productPrice = productPrice;
    }


    // Add this method to calculate the total profit based on sales and margin
    public double calculateTotalProfit() {
        int totalQuantity = 0;

        // Sum up the orderQuantity from all sales records
        if (sales != null) {
            for (Sales sale : sales) {
                totalQuantity += sale.getOrderQuantity();
            }
        }

        // Calculate total profit based on the margin and total order quantity
        double totalProfit = this.margin * totalQuantity;

        return totalProfit;
    }
}