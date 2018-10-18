package com.productmicro.micro.Entities;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Product {
    @Id
    @TableGenerator(name="product", initialValue = 0)


    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product")
    private int id;
    @Column(unique=true)
    @NotNull
    private String productId; //1
    @NotNull
    private String name;  //2
    @NotNull
    private String brand;  //3
    @NotNull
    private int subCategoryId;  //4
    @NotNull
    private double rating;  //5
    @NotNull
    private String status;  //6
    @NotNull
    @Column(length = 2500)
    private String description; //7
    @NotNull
    private double price; //8
    @NotNull
    private double discount ;//9


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



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

