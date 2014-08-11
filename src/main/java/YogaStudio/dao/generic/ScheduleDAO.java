/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.ScheduleEntity;
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
public class ScheduleDAO {
     private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
     public boolean saveSchedule(ScheduleEntity scheduleEntity){
        try{
            sf.getCurrentSession().saveOrUpdate(scheduleEntity);
            return true;
        }catch(Exception ex){}
        return false;
    }
     public ScheduleEntity get(Long id) {
        return (ScheduleEntity) sf.getCurrentSession().get(ScheduleEntity.class, id);
    }
      public boolean removeSchedule(Long Id) {
        try{
            ScheduleEntity ce = get(Id);
            sf.getCurrentSession().delete(ce);
            return true;
        }
        catch(Exception e){
         System.out.println("Remove schedule failed "+e.getMessage());
        }
        return false;
    }
     
    public List<ScheduleEntity> getAllSchedule() {
        
        String hql = "From ScheduleEntity";
        Query q = sf.getCurrentSession().createQuery(hql);
        return q.list(); 
    }

    public List<ScheduleEntity> getAllSchedulesAvailable() {
        String hql = "From ScheduleEntity s where s.assigned = 0";
        Query q = sf.getCurrentSession().createQuery(hql);
        return q.list(); 
    }

    public List<ScheduleEntity> getAllSchedulesUsed() {
        String hql = "From ScheduleEntity s where s.assigned = 1";
        Query q = sf.getCurrentSession().createQuery(hql);
        return q.list();
    }
}
