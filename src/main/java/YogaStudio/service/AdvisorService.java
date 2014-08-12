/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.CustomerDAO;
import YogaStudio.dao.generic.FacultyDAO;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import java.util.List;


/**
 *
 * @author cCao
 */
public class AdvisorService {
    
    private FacultyDAO facultyDao;
    private CustomerDAO customerDao;
    
    public AdvisorService(FacultyDAO facultyDao, CustomerDAO customerDao){

        this.facultyDao = facultyDao;
        this.customerDao = customerDao;
    }   
     
    public List getAdvisees(long facultyId){

        System.out.println(facultyDao);
        FacultyEntity faculty = facultyDao.get(facultyId);
        
        return faculty.getAdvisees();
    }
    
    public FacultyEntity getAdvisor(long customerId){
        
        CustomerEntity customer = customerDao.get(customerId);
        
        return customer.getAdvisor();
    }
    
    public void assignAdvisor(long customerId){      
        FacultyEntity faculty = facultyDao.getNewAdvisor();
        CustomerEntity cs = customerDao.get(customerId);
        faculty.addAdvisee(cs);
        facultyDao.update(faculty);        
    }
}
