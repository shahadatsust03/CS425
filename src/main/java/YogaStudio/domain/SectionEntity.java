/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    
    @ManyToOne
    @JoinColumn
    ClassEntity classEntity;   

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
    
    
    @ManyToMany
    List<ScheduleEntity> scheduleList = new ArrayList<ScheduleEntity>();
     
    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn
    SemesterEntity semester;
    
    @ManyToOne
    @JoinColumn
    FacultyEntity faculty;

    public FacultyEntity getFaculty() {
        return faculty;
    }
    
    public String getWeekDays(){
        String ret = "";
        for(ScheduleEntity schedule : scheduleList ){
            ret += schedule.getDayOfWeek() + ",";
        }
        return ret;
    }
    
     public String getSchedules(){
        String ret = "";
        for(ScheduleEntity schedule : scheduleList ){
            ret += schedule.getId() + ",";
        }
        return ret;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }
    
    @OneToMany(mappedBy="section",cascade={CascadeType.ALL})
    List<EnrollmentEntity> enrollments = new ArrayList<EnrollmentEntity>();
    public int getTotalEnrollment(){
        return enrollments.size();
    }
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

    public String getDescription() {
        return descripton;
    }

    public void setDescripiton(String descripton) {
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

    public List<ScheduleEntity> getScheduleList() {
        return scheduleList;
    }

    public SectionEntity() {
    }

    public void setScheduleList(List<ScheduleEntity> scheduleList) {
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
        scheduleList.add(schedule);
    }
    public void removeSchedule(ScheduleEntity schedule){        
        scheduleList.remove(schedule);
    }

    public SectionEntity(String sectionName, String descripton, String location, int classLimit) {
        this.sectionName = sectionName;
        this.descripton = descripton;
        this.location = location;
        this.classLimit = classLimit;
    }
    
    // caocm
    public String getScheduleText(){
        String text = "";
        for(ScheduleEntity schedule : scheduleList ){
            int day = schedule.getDayOfWeek();
            text += DayOfWeek.of(day).toString();
            text += "    " + schedule.getStartTimeM();
            text += " - " + schedule.getEndTimeM();
            text += "</br>";
        }
        return text;       
    }    
}
