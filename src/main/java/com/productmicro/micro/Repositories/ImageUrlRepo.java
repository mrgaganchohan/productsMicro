package com.productmicro.micro.Repositories;

import com.productmicro.micro.Entities.ImageUrl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ImageUrlRepo extends CrudRepository<ImageUrl, Integer> {
    @Transactional
        //research more
    void deleteByProductId(String productId);

    @Query("SELECT  u.imageName from ImageUrl  u where u =?1")
    List<ImageUrl> findByProductId(String productId);
}
