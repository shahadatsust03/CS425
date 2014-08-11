/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Transcript")
public class TranscriptEntity {
    @Id
    @GeneratedValue
    private Long id;   
    
    //@ManyToOne(cascade = {CascadeType.ALL})
    @ManyToOne()
    @JoinColumn
    CustomerEntity customer;
    
    //@ManyToOne(cascade = {CascadeType.ALL})
    @ManyToOne()
    @JoinColumn
    CustomerEntity classEntity;
    
    private String gradeReceived;
    private String remarks;

    public Long getId() {
        return id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setClassEntity(CustomerEntity classEntity) {
        this.classEntity = classEntity;
    }

    
    public CustomerEntity getClassEntity() {
        return classEntity;
    }

    
   

    public String getGradeReceived() {
        return gradeReceived;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setId(Long id) {
        this.id = id;
    }  

    public void setGradeReceived(String gradeReceived) {
        this.gradeReceived = gradeReceived;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
   

}
