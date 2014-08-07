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
    Set<SectionEntity> sections = new HashSet<SectionEntity>();
    
    @OneToMany(mappedBy="faculty",cascade={CascadeType.ALL})
    Set<CustomerEntity> customers = new HashSet<CustomerEntity>();
    
    @OneToMany(mappedBy="faculty",cascade={CascadeType.ALL})
    List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();  
    
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
     
     
    public void addCustomer(CustomerEntity customer){
        customer.setFaculty(this);
        customers.add(customer);
    }
    
     public void removeCustomer(CustomerEntity customer){
        customer.setFaculty(null);
        sections.remove(customer);
    }    
}
