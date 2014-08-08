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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Class")
public class ClassEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String className;
    private String description;
    private Double fee;
    
    @ManyToMany(mappedBy="prerequisteClasses",cascade={CascadeType.ALL})
    private List<ClassEntity> prerequisite = new ArrayList<ClassEntity>();

    public void setPrerequisite(List<ClassEntity> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public List<ClassEntity> getPrerequisite() {
        return prerequisite;
    }
    
    @OneToMany(mappedBy="yogaClass",cascade={CascadeType.ALL})
    List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();  
 
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="Prerequestie_Class",  
    joinColumns={@JoinColumn(name="Class_Id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="Prerequestie_Id", referencedColumnName="id")})
    private List<ClassEntity> prerequisteClasses = new ArrayList<ClassEntity>();
    
    @OneToMany(mappedBy="classEntity",cascade={CascadeType.ALL})
    private List<SectionEntity> sections = new ArrayList<SectionEntity>();

    @OneToMany(mappedBy="classes",cascade={CascadeType.ALL})
    private Set<PaymentEntity> payments = new HashSet<PaymentEntity>();
    
    @OneToMany(mappedBy="classEn",cascade={CascadeType.ALL})
    private Set<EnrollmentEntity> enrollments = new HashSet<EnrollmentEntity>();

    @OneToOne
    @JoinColumn
    TranscriptEntity transcript;

    public ClassEntity(String className, String description, Double fee) {
        this.className = className;
        this.description = description;
        this.fee = fee;
    }

    public ClassEntity() {
    }
    
    
    public void addEnrollments(EnrollmentEntity enrollment){
        enrollment.setClassEn(this);
        enrollments.add(enrollment);
    }
    
    public void removeEnrollments(EnrollmentEntity enrollment){
        enrollment.setClassEn(null);
        enrollments.add(enrollment);
    }
    
    
    public void addPayments(PaymentEntity payment){
        payment.setClasses(this);
        payments.add(payment);
    }
    
    public void removePayments(PaymentEntity payment){
        payment.setClasses(null);
        payments.remove(payment);
    }
    
    public Long getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

//    public ClassEntity getPrerequisite() {
//        return prerequisite;
//    }
//
//    public void setPrerequisite(ClassEntity prerequisite) {
//        this.prerequisite = prerequisite;
//    }

    public List<ClassEntity> getPrerequisteClasses() {
        return prerequisteClasses;
    }

    public void setPrerequisteClasses(List<ClassEntity> prerequisteClasses) {
        this.prerequisteClasses = prerequisteClasses;
    }

    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void addPrerequisteClasse(ClassEntity classEntity){
       //classEntity.setPrerequisite(this);
       prerequisteClasses.add(classEntity);
    }
    public void removePrerequisteClasse(ClassEntity classEntity){
       //classEntity.setPrerequisite(null);
       prerequisteClasses.remove(classEntity);
    }
    
    public void addPrerequisteClass(ClassEntity classEntity){
       prerequisite.add(classEntity);
    }
    public void removePrerequisteClass(ClassEntity classEntity){
       prerequisite.remove(classEntity);
    }
    
      public void addSection(SectionEntity section){
       section.setClassEntity(this);
       sections.add(section);
    }
    public void removeSection(SectionEntity section){
       section.setClassEntity(null);
       sections.remove(section);
    }
    
}
