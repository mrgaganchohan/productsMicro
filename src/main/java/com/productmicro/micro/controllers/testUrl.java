package com.productmicro.micro.controllers;
import com.productmicro.micro.Entities.ImageUrl;
import com.productmicro.micro.Entities.Product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class testUrl{
    Product product;
    List<String> imageUrls;
    public testUrl(Product product, List<String> imageUrls){
        this.product = product;
        this.imageUrls = imageUrls;
    }
    public Product getProduct(){
        return product;
    }
    public List<String> getList(){
        return imageUrls;
    }
}
