package com.productmicro.micro.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productId;
    private String imageName;

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getImageName() {
        return imageName;
    }
}
