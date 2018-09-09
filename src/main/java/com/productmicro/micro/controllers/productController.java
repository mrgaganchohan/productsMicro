package com.productmicro.micro.controllers;

import com.productmicro.micro.Entities.Product;
import com.productmicro.micro.Repositories.ProductRepository;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// The following imports are used for uploading image
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(path = "/products")
public class productController {
    private static String user = System.getProperty("user.name");
    private static String IMAGES = "/Users/" + user + "/Documents/images/";
    private static int MAX_ALLOWED_IMAGES = 5;
    @Autowired
    private ProductRepository productRepo;
    private static org.apache.commons.logging.Log Log = LogFactory.getLog(productController.class);


    @PostMapping(path = "/add")
    public @ResponseBody
    String addProduct(Product product)  // Removed RequestBody here because it expects json and
    // by removing this I can simply send  www-form-urlencoded codes
    {
        Product productSaver = new Product();

        productSaver.setName(product.getName());
        productSaver.setCategoryId(product.getCategoryId());
        productSaver.setProductId(product.getProductId());//product id from same table in Product ID table
        productSaver.setBrand(product.getBrand());
        productSaver.setImageName(product.getImageName()); // may be needed to change later
        productSaver.setRating(product.getRating());
        Log.info("Works till here");
        productRepo.save(productSaver);
        return "works here";
    }

    @PostMapping(path = "/addImage") //for www-enc- forms , no need to add RequestBody orRequest Param for products
    public @ResponseBody
    String addImage(Product product, @RequestParam("file") MultipartFile file,
                    RedirectAttributes redirectAttributes)  // Removed RequestBody here because it expects json and
    // by removing this I can simply send  www-form-urlencoded codes
    {
        Product productSaver = new Product();

        productSaver.setName(product.getName());
        productSaver.setCategoryId(product.getCategoryId());
        productSaver.setProductId(product.getProductId());//product id from same table in Product ID table
        productSaver.setBrand(product.getBrand());
        productSaver.setImageName(product.getImageName()); // may be needed to change later
        productSaver.setRating(product.getRating());
        productRepo.save(productSaver);

        // Get the format of the image
        String[] splitName = file.getOriginalFilename().split("\\.");
        int numberOfSplits = splitName.length;
        String format = splitName[numberOfSplits - 1];
        if (file.isEmpty()) {
            return "File is empty";
            // redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            //  return "redirect:uploadStatus";
        }

        try {
            File FileToCheckIfExists;
            Path path = null;
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Log.info("Checkpoint 1------------------------");
            Log.info(product.getProductId());
            Log.info(format);
            //Saving image names as ProductId +1 , ProductId+2 , ... until "ProductId"+"5"


            for (int index = 1; index <= MAX_ALLOWED_IMAGES; index++) { //check if the file exists
                /*Here checking if image exists in MAX_ALLOWED_IMAGES folder . if there is an image with
                 * name "last1.jpg" , then "last2.jpg" will be stored , else last3.jpg will be stored,
                 * else last4 until last5.jpg
                 * */
                FileToCheckIfExists = new File(IMAGES + product.getProductId() + index + "." + format);

                if (!FileToCheckIfExists.exists()) // if the file doesn't exist
                {
                    path = Paths.get(IMAGES + product.getProductId() + index + "." + format);
                    break;
                }

            }
            Log.info("Checkpoint 2------------------------");

            Files.write(path, bytes);
            Log.info("Checkpoint 3------------------------");


            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return "redirect:/uploadStatus";
        return "works here";
    }

    //del By ProductID
    @PostMapping(path = "/del") // we can delete a product by productId (instead of simple ID) as each product
    // will have different productID . Easy to retrieve
    public @ResponseBody
    void delProduct(@RequestBody Product product) {
        Product productSaver = new Product();


    }
}
