package com.productmicro.micro.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique=true)
    @NotNull
    private String productId;
    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private String categoryName;
    @NotNull
    private double rating;
    @NotNull
    private String imageName; // this one is not needed . To be deleted later
    @NotNull
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

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public double getRating() {
        return rating;
    }

    public String getImageName() {
        return imageName;
    }//

}

