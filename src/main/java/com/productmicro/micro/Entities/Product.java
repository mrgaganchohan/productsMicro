package com.productmicro.micro.Entities;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
    @Id
    @TableGenerator(name="product", initialValue = 1)

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product")
    private int id;
    @Column(unique=true)
    @NotNull
    private String productId;
    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private int subCategoryId;
    @NotNull
    private double rating;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotNull
    private String status;
    @NotNull
    @Column(length = 2500)
    private String description;
    @NotNull
    private double price;
    @NotNull
    private double discount ;

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    // --------------ManyToOne Relation below
    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public double getRating() {
        return rating;
    }



}

