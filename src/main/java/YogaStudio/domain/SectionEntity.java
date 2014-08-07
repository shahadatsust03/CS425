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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Section")
public class SectionEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String sectionName;
    private String descripton;
    private String location;
    private int classLimit;

    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn
    ClassEntity classEntity;   
    
    @OneToMany(mappedBy="section",cascade={CascadeType.ALL})
    Set<ScheduleEntity> scheduleList = new HashSet<ScheduleEntity>();
     
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn
    SemesterEntity semester;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn
    FacultyEntity faculty;

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }
    
    @OneToMany(mappedBy="section",cascade={CascadeType.ALL})
    List<EnrollmentEntity> enrollments = new ArrayList<EnrollmentEntity>();
    
    public void addEnrollments(EnrollmentEntity enrollment){
        enrollment.setSection(this);
        enrollments.add(enrollment);
    
    }
    
    public void removeEnrollments(EnrollmentEntity enrollment){
        enrollment.setSection(null);
        enrollments.remove(enrollment);
    
    }
    
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getClassLimit() {
        return classLimit;
    }

    public void setClassLimit(int classLimit) {
        this.classLimit = classLimit;
    }

//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public Set<ScheduleEntity> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(Set<ScheduleEntity> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public SemesterEntity getSemester() {
        return semester;
    }

    public void setSemester(SemesterEntity semester) {
        this.semester = semester;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void addSchedule(ScheduleEntity schedule){
        schedule.setSection(this);
        scheduleList.add(schedule);
    }
    public void removeSchedule(ScheduleEntity schedule){
        schedule.setSection(null);
        scheduleList.remove(schedule);
    }
}
