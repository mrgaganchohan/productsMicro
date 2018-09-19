package com.productmicro.micro.Repositories;

import com.productmicro.micro.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT p from Product p where p.categoryName=?1")
    List<Product> findByCategory(String category);

    @Query("SELECT p from Product p where p.rating >?1")
    List<Product> findByRating(double rating);

    // Sort by name
    @Query("SELECT p from Product p order by p.name asc") //figure out how to replace ?1 with rating
    List<Product> sortByName();

    //multiple entries
    List <Product> findProductByCategoryName(String category);
    //only one will be returned as Products are unique
    Product findProductByProductId(String productId);


    @Transactional //research more
    void deleteByProductId(String productId);
}