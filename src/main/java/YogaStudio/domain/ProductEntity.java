/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Product")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int numberOfProducts;
    
    @OneToMany(mappedBy="product",cascade={CascadeType.ALL})
    private List<ProductOrderEntity> productOrders = new ArrayList<ProductOrderEntity>(); 
    //product has many files
    @OneToMany(mappedBy="product",cascade={CascadeType.ALL})
    private List<FileEntity> images = new ArrayList<FileEntity>();
    
    
    public ProductEntity(){}

    public ProductEntity(Long id, String name, String description, Double price, int numberOfProducts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.numberOfProducts = numberOfProducts;
    }
    
    public ProductEntity(String name,String description,double price, int numberOfProducts)
    {
     this.name = name;
     this.description = name;
     this.price = price;
     this.numberOfProducts = numberOfProducts;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
    
    public void addProductOrders(ProductOrderEntity productOrderEntity){
        productOrderEntity.setProduct(this);
        productOrders.add(productOrderEntity);
    }
    
    public void removeProductOrders(ProductOrderEntity productOrderEntity){
        productOrderEntity.setProduct(null);
        productOrders.remove(productOrderEntity);
    }
    
    public void addImage(FileEntity image){
        image.setProduct(this);
        images.add(image);
    }
    public void removeImage(FileEntity image){
        image.setProduct(null);
        images.remove(image);
    }
    
    public FileEntity getFirstImage(){
      return (images.size() >0)?(FileEntity) images.toArray()[0]:null;
    }
    
    public List<ProductOrderEntity> getProductOrders() {
        return productOrders;
    }

    public List<FileEntity> getImages() {
        return images;
    }
}
