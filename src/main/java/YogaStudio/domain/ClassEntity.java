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
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="prereq_id")
    private ClassEntity prerequisite;
    
    @OneToMany(mappedBy="yogaClass")
    List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();  
 
    @OneToMany(mappedBy="prerequisite",cascade={CascadeType.ALL})
    private Set<ClassEntity> prerequisteClasses = new HashSet<ClassEntity>();
    
    @OneToMany(mappedBy="classEntity",cascade={CascadeType.ALL})
    private Set<SectionEntity> sections = new HashSet<SectionEntity>();

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

    public ClassEntity getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(ClassEntity prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Set<ClassEntity> getPrerequisteClasses() {
        return prerequisteClasses;
    }

    public void setPrerequisteClasses(Set<ClassEntity> prerequisteClasses) {
        this.prerequisteClasses = prerequisteClasses;
    }

    public Set<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(Set<SectionEntity> sections) {
        this.sections = sections;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void addPrerequisteClasse(ClassEntity classEntity){
       classEntity.setPrerequisite(this);
       prerequisteClasses.add(classEntity);
    }
    public void removePrerequisteClasse(ClassEntity classEntity){
       classEntity.setPrerequisite(null);
       prerequisteClasses.remove(classEntity);
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
