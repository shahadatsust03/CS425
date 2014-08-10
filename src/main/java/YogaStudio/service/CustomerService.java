/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.CustomerDAO;
import YogaStudio.domain.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author demodem
 */
public class CustomerService {
    @Autowired
    private CustomerDAO customerdao;

    public CustomerService(CustomerDAO customerdao) {
        this.customerdao = customerdao;
    }
     
    public CustomerEntity getCustomer(Long id) {
        return customerdao.get(id);
    }
      
       public CustomerEntity get(Long id) {     
        return customerdao.get(id);      
    }

    public CustomerEntity getUser(Long id) {     
        return customerdao.get(id);      
    }

    public void delete(Long userId) {    
        customerdao.delete(userId);   
    }   
}
