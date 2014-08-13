/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.dao.generic;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.EnrollmentEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.UserEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 */
@Transactional
public class EnrollmentDAO {

    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public EnrollmentEntity get(Long id) {
        return (EnrollmentEntity) sf.getCurrentSession().load(EnrollmentEntity.class, id);
    }

    public List<Long> getEnrolledCustomersIds(Long sectionId) {
        String hql = "SELECT EE.customer.id From EnrollmentEntity EE WHERE EE.section.id = :sectionId";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("sectionId",sectionId);
       

        List<Long> customerIds = q.list();

        return customerIds;
        //return q.list();

    }

    public Long getEnrolledCustomersCount(Long sectionId) {
        String hql = "SELECT COUNT(*) FROM EnrollmentEntity EE WHERE EE.section.id = :sectionId";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("sectionId", sectionId);

        return (Long)(q.uniqueResult());
        //return q.list();

    }
    
     public List<SectionEntity> getEnrolledSections(Long customerId) {
        
        String hql = "SELECT EE.section From EnrollmentEntity EE WHERE EE.customer.id = :customerId AND EE.status = 0";
        Query q = sf.getCurrentSession().createQuery(hql);    
        q.setParameter("customerId", customerId);
        return q.list(); 
    }

    public CustomerEntity getCustoemrAtFrontOfWaitingList(Long sectionId) {
        String hql = "SELECT customer From EnrollmentEntity EE WHERE EE.section.id = :sectionId " +
                "AND EE.enrolledDate = " +
                "(SELECT MIN(EE.enrolledDate) FRom EnrollmentEntity EE WHERE EE.status = 1)";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("sectionId", sectionId);
        
        return (CustomerEntity)q.uniqueResult();
        
        /*
        List<EnrollmentEntity> enrollments = q.list();

        EnrollmentEntity enrollmentOfCustomerAtFrontOfWaitingList = null;
        CustomerEntity customerAtFrontOfWaitingList = null;

        Iterator<EnrollmentEntity> iterator = enrollments.iterator();
        while (iterator.hasNext()) {
            EnrollmentEntity temp = iterator.next();

            if (enrollmentOfCustomerAtFrontOfWaitingList == null) {
                enrollmentOfCustomerAtFrontOfWaitingList = temp;
            } else {
                if (temp.getEnrolledDate().compareTo(enrollmentOfCustomerAtFrontOfWaitingList.getEnrolledDate()) < 0) {
                    enrollmentOfCustomerAtFrontOfWaitingList = temp;
                }
            }
        }

        if (enrollmentOfCustomerAtFrontOfWaitingList != null) {
            customerAtFrontOfWaitingList = enrollmentOfCustomerAtFrontOfWaitingList.getCustomer();
        }

        return customerAtFrontOfWaitingList;*/
    }

    public void saveEnrollment(EnrollmentEntity enrollmentEntity) {
        sf.getCurrentSession().persist(enrollmentEntity);
    }

    public EnrollmentEntity getEnrollment(Long customerId, Long sectionId) {
        //Assuming the className for classes is unique
        String hql = "From EnrollmentEntity EE WHERE EE.customer.id = :customerId AND EE.section.id = :sectionId";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("customerId", customerId);
        q.setParameter("sectionId", sectionId);
        return (EnrollmentEntity) q.uniqueResult();    //ClassEntity)(q.list().get(0));
    }
    
    public void unenroll(Long enrollmentId) {
         EnrollmentEntity u = get(enrollmentId);
        sf.getCurrentSession().delete(u);
    }
    
    //returns true if waiting list is not empty, returns true if there is waiting list
    
    //assuming status = 1 implies in waiting list, 0 implies enrolled
    public boolean waitingListIsEmpty(Long sectionId)  //returns true if the waiting list is not empty
    {    
        String hql = "From EnrollmentEntity EE WHERE EE.section.id = :sectionId  AND EE.status = 1";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setParameter("sectionId", sectionId);
        if(q.list().size() == 0)
            return false;
        else
            return true;
    }
    
    public void removeEnrollment(EnrollmentEntity enrollmentEntity) {
        try{        
        sf.getCurrentSession().delete(enrollmentEntity);
        }
        catch(Exception e){
         System.out.println("Remove class failed "+e.getMessage());
        }
    }

    public List<EnrollmentEntity> getEnrollments(Long id) {
        Query result = sf.getCurrentSession().getNamedQuery("enrollmentByCust");
        result.setParameter("customer", id);                
        List<EnrollmentEntity> enrollList = result.list();
        if (enrollList.isEmpty()) {
            return null;
        }
        return enrollList;
    }

}
