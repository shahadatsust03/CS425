/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Customer")
public class CustomerEntity extends UserEntity{
    @OneToMany(mappedBy="customer",cascade={CascadeType.PERSIST})
    List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();  

    @OneToMany(mappedBy="customer",cascade={CascadeType.PERSIST})
    List<EnrollmentEntity> enrollments = new ArrayList<EnrollmentEntity>();  
    
    @OneToMany(mappedBy="customer",cascade={CascadeType.PERSIST})
    List<OrderEntity> orders = new ArrayList<OrderEntity>();  
    
    
    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn
    FacultyEntity faculty;

    public CustomerEntity() {
    }

    public CustomerEntity(String userName,String password, String fullname, String email) {
        super(userName,password, fullname, email);
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }
    
    public List<WaiverEntity> getWaivers() {
        return waivers;
    }

    public void setWaivers(List<WaiverEntity> waivers) {
        this.waivers = waivers;    
    }
    
    public void addOrder(OrderEntity order) {
        order.setCustomer(this);
        this.orders.add(order);
    }
     public void removeOrder(OrderEntity order) {
        order.setCustomer(null);
        this.orders.add(order);
    }
    
    
    public void addWaivers(WaiverEntity waiver) {
        waiver.setCustomer(this);
        this.waivers.add(waiver);
    }
     public void removeWaivers(WaiverEntity waiver) {
        waiver.setCustomer(null);
        this.waivers.remove(waiver);
    }
    
     public void addEnrollments(EnrollmentEntity enrollment) {
         enrollment.setCustomer(this);
        enrollments.add(enrollment);
    }
     public void removeEnrollments(EnrollmentEntity enrollment) {
        enrollment.setCustomer(null);
         enrollments.remove(enrollment);
    }
}
