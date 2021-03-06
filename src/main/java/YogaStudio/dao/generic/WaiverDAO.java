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

    public WaiverEntity get(Long id) {
        return (WaiverEntity) sf.getCurrentSession().load(WaiverEntity.class, id);
    }

    public void update(WaiverEntity waiver) {

        sf.getCurrentSession().update(waiver);
    }

    public void delete(Long waiverId) {
        WaiverEntity waiver = get(waiverId);
        sf.getCurrentSession().delete(waiver);
    }

    public List<WaiverEntity> getWaiversByFAId(Long faId) {
        Query result = sf.getCurrentSession().getNamedQuery("submittedWaivers");
        result.setParameter("faculty", faId);
        result.setParameter("status", STATUS.SUBMITTED.name());
        //result.setParameter("enabled", enabled);
        List<WaiverEntity> waiverList = result.list();
        if (waiverList.isEmpty()) {
            return null;
        }
        return waiverList;
    }

    public List<WaiverEntity> getWaiversByCustAndClass(Long custId, Long classId) {
        Query result = sf.getCurrentSession().getNamedQuery("waiverByCustAndClass");
        result.setParameter("customer", custId);
        result.setParameter("yogaClass", classId);
        
        result.setParameter("status", STATUS.INVALID.name());
        
        List<WaiverEntity> waiverList = result.list();
        if (waiverList.isEmpty()) {
            return null;
        }
        return waiverList;
    }

    public List<WaiverEntity> getWaiversByCust(CustomerEntity cust) {
        Query result = sf.getCurrentSession().getNamedQuery("waiverByCust");
        result.setParameter("customer", cust.getId());
        result.setParameter("status", STATUS.INVALID.name());
        //result.setParameter("enabled", enabled);
        List<WaiverEntity> waiverList = result.list();
        if (waiverList.isEmpty()) {
            return null;
        }
        return waiverList;
    }
}
