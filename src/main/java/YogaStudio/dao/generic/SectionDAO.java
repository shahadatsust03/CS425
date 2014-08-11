/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.EnrollmentEntity;
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
    public boolean saveSection(SectionEntity sectionEntity){
        try{
            sf.getCurrentSession().saveOrUpdate(sectionEntity);
            return true;
        }catch(Exception ex){}
        return false;
    }
     public SectionEntity get(Long id) {
        return (SectionEntity) sf.getCurrentSession().get(SectionEntity.class, id);
    }
      public boolean removeSection(Long Id) {
        try{
            SectionEntity ce = get(Id);
            sf.getCurrentSession().delete(ce);
            return true;
        }
        catch(Exception e){
         System.out.println("Remove section failed "+e.getMessage());
        }
        return false;
    }
      public boolean removeSection(Long Id) {
        try{
            SectionEntity ce = get(Id);
            sf.getCurrentSession().delete(ce);
            return true;
        }
        catch(Exception e){
         System.out.println("Remove section failed "+e.getMessage());
        }
        return false;
    }
     
    public List<SectionEntity> getAllSections() {
        
        String hql = "From SectionEntity";
        Query q = sf.getCurrentSession().createQuery(hql);
        return q.list(); 
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
    
    public List<ClassEntity> getPrerequisites(Long sectionId) throws ParseException{
        //Assuming the className for classes is unique
        String hql = "SELECT SE.classEntity From SectionEntity SE WHERE SE.id = :sectionId"; 
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("sectionId", sectionId);
        
        ClassEntity classEntity = (ClassEntity) q.uniqueResult();
        
        if(classEntity != null)
            return classEntity.getPrerequisteClasses();
        
        return null;
    }
    
     public boolean isEnrolled(Long customerId, Long sectionId){
        String hql = "From EnrollmentEntity EE WHERE EE.customer.id = :customerId AND EE.section.id = :sectionId";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("customerId", customerId);
        q.setParameter("sectionId", sectionId);
        EnrollmentEntity enrollmentEntity = (EnrollmentEntity) q.uniqueResult();
        if(enrollmentEntity == null)
            return false;
        else
            return true;
    }
    
}
