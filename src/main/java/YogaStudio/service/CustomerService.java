/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;


import YogaStudio.dao.generic.ClassDAO;
import YogaStudio.dao.generic.CustomerDAO;
import YogaStudio.dao.generic.EnrollmentDAO;
import YogaStudio.dao.generic.RegistrationDAO;
import YogaStudio.dao.generic.SectionDAO;
import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.UserEntity;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Shahadat
 */
public class CustomerService {
    
    @Autowired
    private CustomerDAO customerdao;
    
    public CustomerService(){};

    public CustomerService(CustomerDAO customerdao) {
        this.customerdao = customerdao;
    }
    
    
    public CustomerDAO getCustomerdao() {
        return customerdao;
    }

    public void setCustomerdao(CustomerDAO customerdao) {
        this.customerdao = customerdao;
    }
    
    public CustomerEntity getCustomer(Long customerId){
        return customerdao.getCustomer(customerId);
    }
    
     public CustomerEntity get(Long customerId){
        return customerdao.get(customerId);
    }

    public List<CustomerEntity> getAllCustomer() {
        return customerdao.getAll();
    }

    public void removeCustomer(Long id) {
        customerdao.delete(id);
    }
    
}
