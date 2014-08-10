/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Faculty")
public class FacultyEntity extends UserEntity {
    private String specialization;
    private int yearsOfExperience;
    @OneToMany(mappedBy="faculty",cascade={CascadeType.ALL})
    List<SectionEntity> sections = new ArrayList<SectionEntity>();
    
    @OneToMany(mappedBy="faculty",cascade={CascadeType.ALL})
    List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
    
    @OneToMany(mappedBy="faculty",cascade={CascadeType.ALL})
    List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();  

    public FacultyEntity() {
    }

    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }

    public List<WaiverEntity> getWaivers() {
        return waivers;
    }

    public void setWaivers(List<WaiverEntity> waivers) {
        this.waivers = waivers;
    }

    public FacultyEntity(String specialization, int yearsOfExperience) {
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public FacultyEntity(String specialization, int yearsOfExperience,String username, String password, String fullname, String email, String AUTHORITY, Date dateOfBirth, Long contactNum, String street, String city, String state, String country, Long zipcode) {
             super(username, password, fullname, email, AUTHORITY, dateOfBirth, contactNum, street, city, state, country, zipcode);
             this.specialization = specialization;
             this.yearsOfExperience = yearsOfExperience;
    }

    public FacultyEntity(String specialization, int yearsOfExperience, String username, String password, String fullname, String email) {
        super(username, password, fullname, email);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public void addWaivers(WaiverEntity waiver) {
        waiver.setFaculty(this);
        this.waivers.add(waiver);
    }
     public void removeWaivers(WaiverEntity waiver) {
        waiver.setFaculty(null);
        this.waivers.remove(waiver);
    }
    
    public void addSection(SectionEntity section){
        section.setFaculty(this);
        sections.add(section);
    }
    
     public void removeSection(SectionEntity section){
        section.setFaculty(null);
        sections.remove(section);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
     
     
    public void addCustomer(CustomerEntity customer){
        customer.setFaculty(this);
        customers.add(customer);
    }
    
     public void removeCustomer(CustomerEntity customer){
        customer.setFaculty(null);
        sections.remove(customer);
    }    
}
