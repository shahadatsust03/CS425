/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import YogaStudio.domain.CustomerEntity;
import YogaStudio.service.CustomerService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sMazumder
 */
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = {"/customer","/customers/customer"}, method = RequestMethod.GET)
    public ModelAndView getCustomers(HttpServletRequest request) {
       
        try {
            
             List<CustomerEntity> customers = customerService.getAllCustomer();
             return new ModelAndView("customers/customer", "customers", customers);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ModelAndView("results", "message", "No customer found.");
    }
    
    @RequestMapping(value = {"/customer/removeCustomer/{id}","/customers/customer/removeCustomer/{id}"}, method = RequestMethod.GET)
    public ModelAndView removeCustomers(HttpServletRequest request, @PathVariable Long id) {
       
        try {            
             customerService.removeCustomer(id);             
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ModelAndView("results", "message", "No customer found.");
    }

}
