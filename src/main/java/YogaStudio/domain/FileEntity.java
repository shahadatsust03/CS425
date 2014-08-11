/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author eTunkara
 */


@Entity
@Table(name="File")
 public class FileEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String fileType;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinTable(name="Product_Image",  
    joinColumns={@JoinColumn(name="File_Id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="Product_Id", referencedColumnName="id")})
    ProductEntity product;
    
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
   
    public FileEntity() {
        
    }
    public FileEntity( String path) 
    {
       this.path = path;
    }
    
    public FileEntity( String path,String fileType) 
    {
       this.path = path;
       this.fileType = fileType;
    }
    
    public FileEntity(String name, String path,String fileType) 
    {
       this.name = name;
       this.path = path;
       this.fileType = fileType;
    }
}