/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.dao.generic;

import YogaStudio.domain.CustomerEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 */
@Transactional
public class CustomerDAO {

    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    public CustomerEntity getCustomer(Long customerId){
        //Assuming the className for classes is unique
        String hql = "From CustomerEntity CE WHERE CE.id = :customerId";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("customerId", customerId);
        return (CustomerEntity) q.uniqueResult();    //ClassEntity)(q.list().get(0));
    }

    public CustomerEntity get(Long id) {
        CustomerEntity ret = (CustomerEntity) sf.getCurrentSession().get(CustomerEntity.class, id);
        return ret;

    }

    public List<CustomerEntity> getAll() {
        Query q = sf.getCurrentSession().createQuery("from CustomerEntity");
        return q.list();
    }

    public void add(CustomerEntity customerEntity) {
        sf.getCurrentSession().persist(customerEntity);
    }

    public void update(CustomerEntity customerEntity) {

        sf.getCurrentSession().update(customerEntity);
    }

    public void delete(Long customerId) {
        CustomerEntity customer = get(customerId);
        Byte activest = 0;
        customer.setActivests(activest);
    }
    
   
}
