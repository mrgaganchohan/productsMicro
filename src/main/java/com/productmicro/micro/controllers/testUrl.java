package com.productmicro.micro.controllers;
import com.productmicro.micro.Entities.ImageUrl;
import com.productmicro.micro.Entities.Product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class testUrl{
    Product product;
    List<String> list ;
    public testUrl(Product product, List<String> list){
        this.product = product;
        this.list = list;
    }
    public Product getProduct(){
        return product;
    }
    public List<String> getList(){
        return list;
    }
}
