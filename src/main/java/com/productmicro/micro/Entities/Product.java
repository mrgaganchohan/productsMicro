package com.productmicro.micro.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productId;
    private String name;
    private String brand;
    private int category_id;
    private float rating;
    private String imageName;

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

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setRating(float rating) {
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

    public int getCategory_id() {
        return category_id;
    }

    public float getRating() {
        return rating;
    }

    public String getImageName() {
        return imageName;
    }//
}
