/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.UserEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 */
@Transactional
public class ClassDAO {
    
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    public ClassEntity get(Long id) {
        ClassEntity classEntity =  (ClassEntity) sf.getCurrentSession().get(ClassEntity.class, id);
        return classEntity;
    }
    
    
    
    public List<ClassEntity> getClassList() {
        String hql = "From ClassEntity";       
        //Query q = sf.getCurrentSession().createQuery(hql);    
        Query q = sf.openSession().createQuery(hql);        
        return q.list();
    }
    
    public boolean saveClass(ClassEntity classEntity) {
        try{
            sf.getCurrentSession().saveOrUpdate(classEntity);
            return true;
        }catch(Exception ex){
            return false;
        }
      
    }
    
    public ClassEntity getClass(String className){
        //Assuming the className for classes is unique
        String hql = "From ClassEntity CE WHERE CE.className = :className";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("className", className);        
        return (ClassEntity) q.uniqueResult();    //ClassEntity)(q.list().get(0));
    }
    
    //TO DO
    public boolean updateClass(ClassEntity classEntity) {
        try{
            sf.getCurrentSession().update(classEntity);
            return true;
        }catch(Exception ex){
            return false;
        }
        
    }
    
    public List<SectionEntity> getSections(String className){
        String hql = "From SectionEntity SE where SE.classEntity.className = :className";       
        Query q = sf.getCurrentSession().createQuery(hql);  
        q.setParameter("className", className);
        return q.list();
    }
    
    public void removeClass(Long classId) {
        try{
        ClassEntity ce = get(classId);
        sf.getCurrentSession().delete(ce);
        }
        catch(Exception e){
         System.out.println("Remove class failed "+e.getMessage());
        }
    }

       
    
}
