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
    @OneToMany(mappedBy="customer")
    private List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();  

    @OneToMany(mappedBy="customer",cascade={CascadeType.ALL})
    private List<EnrollmentEntity> enrollments = new ArrayList<EnrollmentEntity>();
    
    @OneToMany(mappedBy="customer",cascade={CascadeType.ALL})
    private List<TranscriptEntity> transcripts = new ArrayList<TranscriptEntity>();
    
    @OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<OrderEntity>();  
    
    
    @ManyToOne
    @JoinColumn
    private FacultyEntity faculty;

    public List<EnrollmentEntity> getEnrollments() {
        return enrollments;
    }

    public List<TranscriptEntity> getTranscripts() {
        return transcripts;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setEnrollments(List<EnrollmentEntity> enrollments) {
        this.enrollments = enrollments;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
    
    

    public CustomerEntity(String userName,String password, String fullname, String email) {
        super(userName,password, fullname, email);
    }
    
    public CustomerEntity(){
        
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
        this.getOrders().add(order);
    }
     public void removeOrder(OrderEntity order) {
        order.setCustomer(null);
        this.getOrders().add(order);
    }
    
    
    public void addWaivers(WaiverEntity waiver) {
        waiver.setCustomer(this);
        this.getWaivers().add(waiver);
    }
     public void removeWaivers(WaiverEntity waiver) {
        waiver.setCustomer(null);
        this.getWaivers().remove(waiver);
    }
    
     public void addEnrollments(EnrollmentEntity enrollment) {
         enrollment.setCustomer(this);
        getEnrollments().add(enrollment);
    }
     public void removeEnrollments(EnrollmentEntity enrollment) {
        enrollment.setCustomer(null);
         getEnrollments().remove(enrollment);
    }

    /**
     * @param transcripts the transcripts to set
     */
    public void setTranscripts(List<TranscriptEntity> transcripts) {
        this.transcripts = transcripts;
    }
}
