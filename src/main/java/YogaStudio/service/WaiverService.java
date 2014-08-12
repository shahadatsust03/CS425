/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.service;

import YogaStudio.dao.generic.CustomerDAO;
import YogaStudio.dao.generic.WaiverDAO;
import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.EnrollmentEntity;
import YogaStudio.domain.WaiverEntity;
import YogaStudio.util.STATUS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author devika
 */
public class WaiverService {

    @Autowired
    private WaiverDAO waiverDAO;
    @Autowired
    private CustomerDAO customerdao;

    public WaiverService() {
    }

    public WaiverService(WaiverDAO waiverDAO) {
        this.waiverDAO = waiverDAO;
    }

    public WaiverService(WaiverDAO waiverDAO, CustomerDAO customerDao) {
        this.waiverDAO = waiverDAO;
        this.customerdao = customerDao;
    }

    public WaiverDAO getWaiterdao() {
        return waiverDAO;
    }

    public void setWaiterdao(WaiverDAO waiverDAO) {
        this.waiverDAO = waiverDAO;
    }

    public boolean submitWaiverRequest(WaiverEntity waiver, Long custId) {
        if (waiver == null) {
            return false;
        }
        CustomerEntity customer = customerdao.get(custId);
        if (customer != null) {
            waiver.setFaculty(customer.getFaculty());
        }
        waiverDAO.add(waiver);
        return true;
    }

    public boolean verifyWaiverRequest() {
        //need to verify whether waiver is for valid class in this semester
        return true;
    }
    
    public boolean rejectWaiverRequest(Long waiverID,String comments) {
        WaiverEntity waiver = waiverDAO.get(waiverID);
        if (waiver != null) {
            waiver.setStatus(STATUS.REJECTED.name());
            waiver.setComments(comments);
            waiver.setUpdateDate(new Date());
            waiverDAO.update(waiver);            
            return true;
        }
        return false;
    }

    public boolean approveWaiverRequest(Long waiverID,String comments) {
        WaiverEntity waiver = waiverDAO.get(waiverID);
        if (waiver != null) {
            waiver.setStatus(STATUS.APPROVED.name());
            waiver.setUpdateDate(new Date());
            waiver.setComments(comments);
            waiverDAO.update(waiver);
            addClass(waiver);
            return true;
        }
        return false;
    }

    public void addClass(WaiverEntity waiver) {
        byte b = 1;        
        //need to get class and add to customer        
        EnrollmentEntity enroll = new EnrollmentEntity(b, waiver.getCustomer(), waiver.getYogaClass());
        //Do we need to persist Enroll ?
        waiver.getYogaClass().addEnrollments(enroll);
        waiver.getCustomer().addEnrollments(enroll);
        waiverDAO.update(waiver);

    }

    public List<WaiverEntity> getWaiversByFaId(Long faId) {
        return waiverDAO.getWaiversByFAId(faId);
    }
    
    public WaiverEntity getWaiver(Long id){
    return waiverDAO.get(id);
    }

    public List<WaiverEntity> getWaiversByCustAndClass(Long custId, Long classId) {

        return waiverDAO.getWaiversByCustAndClass(custId, classId);
    }

    public List<WaiverEntity> getWaiversByCust(CustomerEntity cust) {
        List<WaiverEntity> waiverList = new ArrayList<WaiverEntity>();
        waiverList = waiverDAO.getWaiversByCust(cust);
        return waiverList;
    }

}
