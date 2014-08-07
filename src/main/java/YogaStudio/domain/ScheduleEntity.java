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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    private Long id;
    private int dayOfWeek;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate; 
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate; 
     
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn
    SectionEntity  section;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setSection(SectionEntity section){
        this.section = section;
    }
}
