package com.productmicro.micro.Repositories;

import com.productmicro.micro.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p from Product p where p.subCategoryId=?1")
    List<Product> findByCategory(int category);

    @Query("SELECT p from Product p where p.rating >=?1")
    List<Product> findByRating(double rating);

    // Sort by name
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> searchByName(String name);

    @Query("SELECT p from Product p order by p.name asc") //figure out how to replace ?1 with rating
    List<Product> sortByName();

    @Query("SELECT p from Product p ") //figure out how to replace ?1 with rating

//    Page<Product> all(Pageable Page);
    //multiple entries
    List <Product> findProductBySubCategoryId(int subCategoryId);
    //only one will be returned as Products are unique
    Product findProductById(int productId);
    Product findProductByProductId(String productId);
    //find id of the product where ProductId is
//    @Query("Select p.id from Product p where p.productId=?1")
  //  int  findIdByProductId(String productId);
    @Transactional //research more
    void deleteById(int id);



//    Page<Product> ProductList(Pageable pageable);
}