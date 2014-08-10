/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.dao.generic;

import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.WaiverEntity;
import YogaStudio.util.STATUS;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author devika
 */
@Transactional
public class WaiverDAO {

    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public List<WaiverEntity> getAll() {
        Query q = sf.getCurrentSession().createQuery("from WaiverEntity");
        return q.list();
    }

    public void add(WaiverEntity waiver) {
        sf.getCurrentSession().persist(waiver);
    }

    public WaiverEntity get(int id) {
        return (WaiverEntity) sf.getCurrentSession().load(WaiverEntity.class, id);
    }

    public void update(WaiverEntity waiver) {

        sf.getCurrentSession().update(waiver);
    }

    public void delete(int waiverId) {
        WaiverEntity waiver = get(waiverId);
        sf.getCurrentSession().delete(waiver);
    }

     public List<WaiverEntity> getWaiversByFAId(Long faId) {
        Query result = sf.getCurrentSession().getNamedQuery("submittedWaivers");
        result.setParameter("faculty", faId);
        result.setParameter("status", STATUS.SUBMITTED.name());
        //result.setParameter("enabled", enabled);
        if (result.list().isEmpty()) {
            return null;
        }
        return result.list();
    }
    
    public List<WaiverEntity> getWaiversByCustAndClass(Long custId, Long classId) {
        Query result = sf.getCurrentSession().getNamedQuery("waiverByCustAndClass");
        result.setParameter("customer", custId);
        result.setParameter("yogaClass", classId);
        result.setParameter("status", STATUS.INVALID.name());
        //result.setParameter("enabled", enabled);
        if (result.list().isEmpty()) {
            return null;
        }
        return result.list();
    }
}
