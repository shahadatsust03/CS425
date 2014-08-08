/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.ProductDAO;
import YogaStudio.domain.ProductEntity;
import YogaStudio.domain.UserEntity;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eTunkara
 */
public class ProductService {
   
    @Autowired
    private ProductDAO productDao;
      
    public ProductService(){}
    
    public ProductService(ProductDAO productDao){
        this.productDao= productDao;      
    }
    
    public List<ProductEntity> getAll() {
        return productDao.getAll();     
    }

    public boolean add(String name, String description,int numberOfProducts,double price) 
    {    
        ProductEntity product = new ProductEntity(name,description,price,numberOfProducts);
        //set the user authority      
        return productDao.add(product);    
    }
    public boolean add(Long id, String name, String description,int numberOfProducts,double price) 
    {    
        
        ProductEntity product = new ProductEntity(id, name,description,price,numberOfProducts);
        //set the user authority      
        return productDao.add(product);    
    }
    public List<ProductEntity> findBy(String fieldName,String value) 
    {           
      return productDao.findBy(fieldName, value);
    }

    public ProductEntity get(Long id) {     
        return productDao.get(id);      
    }

    public void update(int userId, UserEntity userentity) {     
        //productDao.update(userId, userentity);    
    }

    public void delete(Long userId) {    
        productDao.delete(userId);   
    }   
}
