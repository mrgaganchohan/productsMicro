package com.productmicro.micro.Repositories;

import com.productmicro.micro.Entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT p from Product p where p.categoryName=?1")
    List<Product> findByCategory(String category);

@Query ("SELECT p from Product p where p.rating >?1")
List<Product> findByRating(double rating);

}
