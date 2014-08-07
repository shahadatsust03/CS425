/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name = "Enrollment")
public class EnrollmentEntity {

    @Id
    @GeneratedValue
    private Long id;

    public EnrollmentEntity() {
    }

    public EnrollmentEntity(Byte status, CustomerEntity customer, ClassEntity classEn) {
        this.status = status;
        this.enrolledDate = enrolledDate;
        this.customer = customer;
        this.classEn = classEn;
        this.enrolledDate = new Date();
    }
    private Byte status;
    @Temporal(TemporalType.DATE)
    Date enrolledDate;

    public EnrollmentEntity(Byte status, CustomerEntity customer, SectionEntity section, ClassEntity classEn) {
        this.status = status;
        this.customer = customer;
        this.section = section;
        this.classEn = classEn;
        this.enrolledDate = new Date();
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    CustomerEntity customer;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    SectionEntity section;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    ClassEntity classEn;

    public ClassEntity getClassEn() {
        return classEn;
    }

    public void setClassEn(ClassEntity classEn) {
        this.classEn = classEn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }

}
