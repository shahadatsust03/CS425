/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.UserEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 */
@Transactional
public class SectionDAO {
    
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
     public SectionEntity get(int id) {
        return (SectionEntity) sf.getCurrentSession().load(SectionEntity.class, id);
    }
     
    public FacultyEntity getAssignedFaculty(Long sectionId) {
        SectionEntity sectionEntity = (SectionEntity) sf.getCurrentSession().load(SectionEntity.class, sectionId);
        return sectionEntity.getFaculty();
    }
    
    public SectionEntity getSection(String sectionName, String location, int classLimit, String startDate, String endDate) throws ParseException{
        //Assuming the className for classes is unique
        String hql = "From SectionEntity SE WHERE SE.sectionName = :sectionName " + 
        " AND SE.location = :location AND SE.classLimit = :classLimit" +
        " AND SE.semester.startDate = :startDate AND SE.semester.endDate = :endDate";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("sectionName", sectionName);
        q.setParameter("location", location);
        q.setParameter("classLimit", classLimit);
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        q.setParameter("startDate", formatter.parse(startDate));
        q.setParameter("endDate", formatter.parse(endDate));
        return (SectionEntity) q.uniqueResult();    //ClassEntity)(q.list().get(0));
    }
    
}
