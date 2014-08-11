/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.TranscriptEntity;
import YogaStudio.domain.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 */
@Transactional
public class TranscriptDAO {
    
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    public TranscriptEntity get(Long id) {
        return (TranscriptEntity) sf.getCurrentSession().load(TranscriptEntity.class, id);
    }
    
    public ArrayList<ClassEntity> getListOfCoursesTaken(Long customerId){
        String hql = "From ClassEntity CE WHERE CE.id in (" +
                "SELECT TE.classEntity.id From TranscriptEntity TE WHERE TE.customer.id = :customerId)";
                
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("customerId", customerId);
        
        return (ArrayList<ClassEntity>) q.list();
    }

}
