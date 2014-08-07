/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Semester")
public class SemesterEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String semesterName;
    @Temporal(TemporalType.DATE)
    private Date startDate; 
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(mappedBy="semester",cascade={CascadeType.ALL})
//    @JoinColumn
    Set<SectionEntity> sections = new HashSet<SectionEntity>();

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(Set<SectionEntity> sections) {
        this.sections = sections;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void addSection(SectionEntity section){
        section.setSemester(this);
        sections.add(section);
    }
     public void removeSection(SectionEntity section){
        section.setSemester(null);
        sections.remove(section);
    }
}
