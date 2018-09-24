package com.productmicro.micro.controllers;

import com.productmicro.micro.Entities.ImageUrl;
import com.productmicro.micro.Entities.Product;
import com.productmicro.micro.Repositories.ImageUrlRepo;
import com.productmicro.micro.Repositories.ProductRepository;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin  //Access-control-allow-origin
@SuppressWarnings("unchecked")

@Controller
@RequestMapping(path = "/products")
public class productController  {
    private static String user = System.getProperty("user.name");

    private static String IMAGES="/opt/imgs/images/";
    private static int MAX_ALLOWED_IMAGES = 5;
    @Autowired
    private    ProductRepository productRepo;
    @Autowired
    private   ImageUrlRepo imageRepo;
    private static org.apache.commons.logging.Log Log = LogFactory.getLog(productController.class);


    @GetMapping (path="/displayProducts") // no images
    public ResponseEntity displayProducts()
    {
        Iterable<Product> allProducts =productRepo.findAll();

        if (allProducts==null)
        {
            return new ResponseEntity("No Product Exists", HttpStatus.NOT_FOUND);

        }
        Log.info("Total number of Products is ----");
        return  new ResponseEntity(        getArraysOfProducts(allProducts),HttpStatus.OK);
    }





    private  ArrayList<testUrl> getArraysOfProducts( Iterable<Product> ListOfProductsInputted)
    {            ArrayList<testUrl> toBeOutputAllProducts=new ArrayList<testUrl>();


        for (Product retreivedProducts: ListOfProductsInputted)
        {
            int pid = productRepo.findIdByProductId(retreivedProducts.getProductId());

            List <ImageUrl> result = imageRepo.findImageUrlsByProductId(pid);
            List<String> store = new ArrayList<String>();
            for (int i=0; i<result.size(); i++){
                store.add(result.get(i).getImageName());
            }
            toBeOutputAllProducts.add(new testUrl(retreivedProducts,store));
        }
        return toBeOutputAllProducts;
    }
    private static void checkOS()
    {
        String os=System.getProperty("os.name");
        if (os.equals("Mac OS X"))
        {
            IMAGES = "/Users/" + user + "/Documents/images/";
            Log.info("OSX DETECTED--------");}
        else{
            IMAGES = "/opt/imgs/images/";
            Log.info("Linux Detected---------");
        }
    }
// Adding a product and it's information . Works Fine.
// ADD A NEW PRODUCT
    @PostMapping(path = "/addImage") //for www-enc- forms , no need to add RequestBody orRequest Param for products
    public @ResponseBody
    //make sure the RequestParam has the same file name as the array of images being passed by the form.
    //So input file in the form should have the same name.
    ResponseEntity addImage(Product product, @RequestParam("file") MultipartFile [] file,
                            RedirectAttributes redirectAttributes)  // Removed RequestBody here because it expects json and
    // by removing this I can simply send  www-form-urlencoded codes
    {
       checkOS();
        Product productSaver = new Product();
// Return if statement in front end so that if more than 5 images are selected it gives an error
        // CHECK IF It already exists , the ProductId

        Product exists = productRepo.findProductByProductId(product.getProductId());
        String proId= product.getProductId();

        if (exists!=null)
        {
            return new ResponseEntity("Product Id ="+proId +" already exists.", HttpStatus.CONFLICT);

        }

        productSaver.setName(product.getName());
        productSaver.setCategoryName(product.getCategoryName());
        productSaver.setProductId(product.getProductId());//product id from same table in Product ID table
        productSaver.setBrand(product.getBrand());
       // productSaver.setImageNameP(product.getImageNameP()); // may be needed to change later
        productSaver.setRating(product.getRating());
        productSaver.setDescription(product.getDescription());
        productSaver.setDiscount(product.getDiscount());
        productSaver.setPrice(product.getPrice());
        Log.info("SAvinf product");
        productRepo.save(productSaver);
        int length = file.length;
        Log.info(length+"------Length printed");
        // loop through all given images.
        if (length !=0)// only run the following for loop if the length is not 0
            for (int currentImage = 0; currentImage < length; currentImage++){
            // Get the format of the image
                if (file[currentImage].isEmpty()) {
                    continue;

                }
               String[] splitName = file[currentImage].getOriginalFilename().split("\\.");

        int numberOfSplits = splitName.length;
        String format = splitName[numberOfSplits - 1];


        try {
            Log.info("Checkpoint 1------------------------");

            File FileToCheckIfExists;
            Path path = null;
            // Get the file and save it somewhere
            byte[] bytes = file[currentImage].getBytes();
            Log.info(product.getProductId());
            Log.info(format);
            //Saving image names as ProductId +1 , ProductId+2 , ... until "ProductId"+"5"

            String [] allowedFormats = {"jpg","png","jpeg","JPG","PNG","JPEG"};
            String imageUrl = "https://elixir.ausgrads.academy/images/";
                int imageIndex=currentImage+1;
         //       FileToCheckIfExists = new File(IMAGES + product.getProductId() + imageIndex + "."+format);
            imageUrl=imageUrl+product.getProductId() + imageIndex + "."+format;
                    path = Paths.get(IMAGES + product.getProductId() + imageIndex + "." + format);

                    // SAVING Imageurls in ImageURL table , so that we can access images easily by providing the correct ProductId
            ImageUrl imageUrlSaver = new ImageUrl();

            imageUrlSaver.setImageName(imageUrl); // Saving the url
            imageUrlSaver.setProduct(productSaver);
            imageRepo.save(imageUrlSaver);
                    Files.write(path, bytes);




            Log.info("Checkpoint 2------------------------");

            Log.info("Checkpoint 3------------------------");


            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        return  new ResponseEntity(productSaver, HttpStatus.CREATED);
    }

    @GetMapping(path="/delete/{productId}")
    public ResponseEntity delProduct(@PathVariable  String productId) {

        Product exists = productRepo.findProductByProductId(productId);
        if (exists==null)
        {
            return new ResponseEntity("Product Id ="+productId +" doesn't exist", HttpStatus.NOT_FOUND);

        }
        int pid = productRepo.findIdByProductId(productId);
        imageRepo.deleteImageUrlsByProductId(pid);

        productRepo.deleteByProductId(productId);
        return new ResponseEntity("Deleted '" + productId + "' successfully.", HttpStatus.OK);
    }
    @GetMapping(path="/getByProductId/{productId}")
    public ResponseEntity
    getByProductId(@PathVariable String productId)
    {     // pid is the pid column that is the foreign key.
        Product reqProduct = productRepo.findProductByProductId(productId);
        if (reqProduct==null)

        {
            return new ResponseEntity("Product Id ="+productId +" doesn't exist", HttpStatus.NOT_FOUND);

        }
          int pid = productRepo.findIdByProductId(productId);


        List <ImageUrl> result = imageRepo.findImageUrlsByProductId(pid);
        List<String> store = new ArrayList<String>();
        for (int i=0; i<result.size(); i++){
            store.add(result.get(i).getImageName());
        }
        testUrl temp = new testUrl(reqProduct,store);

        //Log.info("WORKS FINE UNTIL THIS POINT");
        return new ResponseEntity(temp,HttpStatus.OK);
    }
    //del By ProductID
    @GetMapping(path="/getImageUrl/{productId}")
    public ResponseEntity
    getImageUrl(@PathVariable String productId)
    {     // pid is the pid column that is the foreign key.
        Product exists = productRepo.findProductByProductId(productId);
        if (exists==null)

        {
            return new ResponseEntity("Product Id ="+productId +" doesn't exist", HttpStatus.NOT_FOUND);

        }
        int pid = productRepo.findIdByProductId(productId);
        Log.info("WORKS FINE UNTIL THIS POINT");
        List <ImageUrl> result = imageRepo.findImageUrlsByProductId(pid);
        return new ResponseEntity(result,HttpStatus.OK);
    }
    @GetMapping(path="/orderByName")

    public ResponseEntity
     sortByName()
    {

        Iterable <Product> productSort =  productRepo.sortByName();

        if (productSort==null)
        {
            return new ResponseEntity("No Product Exists", HttpStatus.NOT_FOUND);

        }
        Log.info("Total number of Products is ----");
        return  new ResponseEntity(        getArraysOfProducts(productSort),HttpStatus.OK);
    }

    @GetMapping(path="/getByCategory/{category}")

    public ResponseEntity
      findByCategoryP(@PathVariable String category)
    {

        List <Product> allProducts = productRepo.findProductByCategoryName(category);

        if (allProducts==null)
        {
            return new ResponseEntity("No Product Exists", HttpStatus.NOT_FOUND);

        }
        Log.info("Total number of Products is ----");
        return  new ResponseEntity(        getArraysOfProducts(allProducts),HttpStatus.OK);
    }

    @GetMapping(path="/getByRating/{rating}")

    public ResponseEntity  findByRatingP(@PathVariable double rating)
    {
        Iterable<Product> allProducts= productRepo.findByRating(rating);

        if (allProducts==null)
        {
            return new ResponseEntity("No Product Exists", HttpStatus.NOT_FOUND);

        }
        Log.info("Total number of Products is ----");
        return  new ResponseEntity(getArraysOfProducts(allProducts),HttpStatus.OK);
    }
}


