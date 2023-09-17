package com.pdt_sales.pdt_sales.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Column(name = "ProductKey")
    private Long productKey;

    // ProductName Column
    @Column(name = "ProductName")
    @NotBlank(message = "Product name is mandatory")
    @Size(max = 255, message = "Product name should be less than or equal to 255 characters")
    private String productName;

    // ModelName Column
    @Column(name = "ModelName")
    @Size(max = 255, message = "Model name should be less than or equal to 255 characters")
    private String modelName;

    // ProductDescription Column
    @Column(name = "ProductDescription")
    @Size(max = 1000, message = "Product description should be less than or equal to 1000 characters")
    private String productDescription;

    // ProductColor Column
    @Column(name = "ProductColor")
    @NotBlank(message = "Product color is mandatory")
    @Size(max = 255, message = "Product color should be less than or equal to 255 characters")
    private String productColor;

    // ProductSize Column
    @Column(name = "ProductSize")
    @NotBlank(message = "Product size is mandatory")
    @Size(max = 50, message = "Product size should be less than or equal to 50 characters")
    private String productSize;

    // ProductCost Column
    @Column(name = "ProductCost")
    @NotNull(message = "Product cost is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product cost must be greater than 0")
    private double productCost;

    // ProductPrice Column
    @Column(name = "ProductPrice")
    @NotNull(message = "Product price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than 0")
    private double productPrice;

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

}
