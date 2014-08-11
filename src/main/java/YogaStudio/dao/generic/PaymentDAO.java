/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.PaymentEntity;
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
public class PaymentDAO {
    
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    public PaymentEntity get(Long id) {
        return (PaymentEntity) sf.getCurrentSession().load(PaymentEntity.class, id);
    }
    
    public PaymentEntity getPayment(PaymentEntity paymentEntity){
        //Assuming the className for classes is unique
        String hql = "From PaymentEntity PE WHERE PE.paymentDate = :paymentDate AND EE.amount = :amount";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("paymentDate", paymentEntity.getPaymentDate());   
        q.setParameter("amount", paymentEntity.getAmount());
        return (PaymentEntity) q.uniqueResult();    //ClassEntity)(q.list().get(0));
    }
    
    public void savePayment(PaymentEntity paymentEntity) {
        sf.getCurrentSession().persist(paymentEntity);
    }
}
