/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.SemesterEntity;
import YogaStudio.domain.UserEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 */
@Transactional
public class SemesterDAO {
    
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    public SemesterEntity get(Long id) {
        return (SemesterEntity) sf.getCurrentSession().get(SemesterEntity.class, id);
    }
    
    public List<SemesterEntity> getSemesterList() {
        String hql = "From SemesterEntity";       
        //Query q = sf.getCurrentSession().createQuery(hql);    
        Query q = sf.openSession().createQuery(hql);        
        return q.list();
    }
    
    public boolean saveSemester(SemesterEntity semesterEntity) {
        try{
            sf.getCurrentSession().saveOrUpdate(semesterEntity);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean updateSemester(SemesterEntity semesterEntity) {
        try{
            sf.getCurrentSession().update(semesterEntity);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public SemesterEntity getSemester(String semesterName, String startDate, String endDate) throws ParseException{
        //Assuming the className for classes is unique
        String hql = "From SemesterEntity SE WHERE SE.semesterName = :semesterName" +
               " AND SE.startDate = :startDateDate AND SE.endDate = :endDateDate";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateDate = formatter.parse(startDate);
        Date endDateDate = formatter.parse(endDate);
        
        Query q = sf.getCurrentSession().createQuery(hql);        
        q.setParameter("semesterName", semesterName);
        q.setParameter("startDateDate", startDateDate);
        q.setParameter("endDateDate", endDateDate);
        return (SemesterEntity) q.uniqueResult();    //ClassEntity)(q.list().get(0));
    }
    
    
    public void updateSemester(Long semesterId, SemesterEntity semesterEntity) {
        String hql = "UPDATE SemesterEntity set semesterName = :semesterName,startDate = :startDate,"  + 
                "endDate = :endDate" + 
             " WHERE id = :semesterId";
        Query query = sf.getCurrentSession().createQuery(hql);
        query.setParameter("semesterName", semesterEntity.getSemesterName());
        query.setParameter("startDate", semesterEntity.getStartDate());
        query.setParameter("endDate", semesterEntity.getEndDate());
        query.setParameter("semesterId", semesterId);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
        
    }
    
    public void removeSemester(Long semesterId) {
        
       // SemesterEntity se = get(semesterId);
        //sf.getCurrentSession().delete(se);
        
        String hql = "DELETE FROM SemesterEntity "  + 
             "WHERE id = :semesterId";
        Query query = sf.getCurrentSession().createQuery(hql);
        query.setParameter("semesterId", semesterId);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
    }

       
    
}
