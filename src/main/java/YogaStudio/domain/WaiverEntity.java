/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.domain;

import YogaStudio.util.STATUS;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@NamedQueries({
    @NamedQuery(name = "submittedWaivers", query = "select w from WaiverEntity w where w.faculty=:faculty and w.status=:status"),
    @NamedQuery(name = "waiverByCustAndClass", query = "select w from WaiverEntity w where w.customer=:customer and w.yogaClass=:yogaClass and w.status <> :status"),
@NamedQuery(name = "waiverByCust", query = "select w from WaiverEntity w where w.customer=:customer and w.status <> :status")})
@Entity
@Table(name = "Waiver")
public class WaiverEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String comments;
    private String status;
    private String reason;
    @Temporal(TemporalType.DATE)
    private Date submissionDate;
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private CustomerEntity customer;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private ClassEntity yogaClass;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private FacultyEntity faculty;


    public WaiverEntity() {
    }

    public WaiverEntity(CustomerEntity customer, String reason, ClassEntity yogaClass) {
        this.customer = customer;
        this.reason = reason;
        this.yogaClass = yogaClass;
        this.submissionDate = new Date();
        this.status = STATUS.SUBMITTED.name();
    }    
    public WaiverEntity(CustomerEntity customer, FacultyEntity faculty, String reason, ClassEntity yogaClass) {
        this.customer = customer;
        this.faculty = faculty;
        this.status = STATUS.SUBMITTED.name();
        this.reason = reason;
        this.yogaClass = yogaClass;
        this.submissionDate = new Date();
        this.status = STATUS.SUBMITTED.name();
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the submissionDate
     */
    public Date getSubmissionDate() {
        return submissionDate;
    }

    /**
     * @param submissionDate the submissionDate to set
     */
    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    /**
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the yogaClass
     */
    public ClassEntity getYogaClass() {
        return yogaClass;
    }

    /**
     * @param yogaClass the yogaClass to set
     */
    public void setYogaClass(ClassEntity yogaClass) {
        this.yogaClass = yogaClass;
    }

}
