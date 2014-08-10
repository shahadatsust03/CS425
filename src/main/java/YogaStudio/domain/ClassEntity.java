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
    
    @ManyToMany(mappedBy="prerequiste",cascade={CascadeType.PERSIST})
    private List<ClassEntity> successor = new ArrayList<ClassEntity>();
    
    @OneToMany(mappedBy="yogaClass",cascade={CascadeType.PERSIST})
    List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();  
 
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name="Prerequestie_Class",  
    joinColumns={@JoinColumn(name="Class_Id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="Prerequestie_Id", referencedColumnName="id")})
    private List<ClassEntity> prerequiste = new ArrayList<ClassEntity>();
    
    @OneToMany(mappedBy="classEntity",cascade={CascadeType.PERSIST})
    private List<SectionEntity> sections = new ArrayList<SectionEntity>();

    @OneToMany(mappedBy="classes",cascade={CascadeType.PERSIST})
    private List<PaymentEntity> payments = new ArrayList<PaymentEntity>();
    
    @OneToMany(mappedBy="classEn",cascade={CascadeType.PERSIST})
    private List<EnrollmentEntity> enrollments = new ArrayList<EnrollmentEntity>();

    @OneToOne(cascade={CascadeType.PERSIST})
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
        return prerequiste;
    }

    public void setPrerequisteClasses(List<ClassEntity> prerequisteClasses) {
        this.prerequiste = prerequisteClasses;
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
       prerequiste.add(classEntity);
    }
    public void removePrerequisteClasse(ClassEntity classEntity){
       //classEntity.setPrerequisite(null);
       prerequiste.remove(classEntity);
    }
    
    public void addPrerequisteClass(ClassEntity classEntity){
       successor.add(classEntity);
    }
    public void removePrerequisteClass(ClassEntity classEntity){
       successor.remove(classEntity);
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
