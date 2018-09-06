package com.productmicro.micro.controllers;

import com.productmicro.micro.Entities.Product;
import com.productmicro.micro.Repositories.ProductRepository;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/products")
public class productController {
@Autowired
    private ProductRepository productRepo;
    private static org.apache.commons.logging.Log Log = LogFactory.getLog(productController.class);


    @PostMapping(path="/add")
    public @ResponseBody void addProduct(@RequestBody Product product)
    {
        Product productSaver = new Product();
        productSaver.setName(product.getName());
        productSaver.setCategory_id(product.getCategory_id());
        productSaver.setProductId(product.getProductId());//product id from same table in Product ID table
        productSaver.setBrand(product.getBrand());
        productSaver.setImageName(product.getImageName()); // may be needed to change later
        productSaver.setRating(product.getRating());
        Log.info("Works till here");
    }
    //del By ProductID
    @PostMapping(path="/del") // we can delete a product by productId (instead of simple ID) as each product
    // will have different productID . Easy to retrieve
          public @ResponseBody void delProduct(@RequestBody Product product)
    {
        Product productSaver = new Product();


    }
}
