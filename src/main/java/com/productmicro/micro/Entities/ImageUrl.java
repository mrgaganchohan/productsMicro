package com.productmicro.micro.Entities;

import javax.persistence.*;

@Entity
public class ImageUrl {
    @Id
    @TableGenerator(name="image_url", initialValue = 0)

    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "image_url")
    private int id;
    private String imageName;

    public void setId(int id) {
        this.id = id;
    }
// THIS HAS FOREIGN KEY which is PRODUCTID
    @ManyToOne
    @JoinColumn(name="pid",
            referencedColumnName = "id"
    )
    private Product product;

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return product.getProductId();
    }
}
