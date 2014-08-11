/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;


import YogaStudio.Controller.EmailController;
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
public class ClassService {
    
    @Autowired
    private ClassDAO classdao;
    
    @Autowired
    private EnrollmentDAO enrollmentdao;
    
    @Autowired
    private CustomerDAO customerdao;
    
    @Autowired
    private SectionDAO sectiondao;
    
    
    public ClassService(){}
    
    public ClassService(ClassDAO classdao, EnrollmentDAO enrollmentdao, CustomerDAO customerdao, SectionDAO sectiondao){
    
        this.classdao = classdao;
        this.enrollmentdao = enrollmentdao;
        this.customerdao = customerdao;
        this.sectiondao = sectiondao;
        
    }
    
    public ClassDAO getClassdao() {
        return classdao;
    }

    public void setClassdao(ClassDAO classdao) {
        this.classdao = classdao;
    }
    
    
    public List<ClassEntity> getClassList(){
        return classdao.getClassList();
    }
    
    public boolean saveClass(ClassEntity classEntity) {
       return classdao.saveClass(classEntity);
    }
    
    public ClassEntity getClass(String className){
        return classdao.getClass(className);
    }
    
     public ClassEntity getClass(Long id){
        return classdao.get(id);
    }
    
    public boolean updateClass(ClassEntity classEntity) {
        return classdao.updateClass(classEntity);
    }
    
    public void removeClass(String  className){
        ClassEntity classEntity = classdao.getClass(className);
        
        Iterator<SectionEntity> sectionsIterator = classEntity.getSections().iterator();
        
        while(sectionsIterator.hasNext())
        {
            SectionEntity section = sectionsIterator.next();
            List<Long> enrolledCustomersIds = enrollmentdao.getEnrolledCustomersIds(section.getId());            
            Iterator<Long> enrolledCustomersIdsIterator = enrolledCustomersIds.iterator();
            while(enrolledCustomersIdsIterator.hasNext()){
                Long id = enrolledCustomersIdsIterator.next();
                
                CustomerEntity customerEntity = customerdao.getCustomer(id);
                
                EmailController eController = new EmailController();
                //eController.sendEmail(customerEntity.getEmail(), "Class Removal" , "The class " + className + " is removed");
                
            }
            
            FacultyEntity facultyEntity = sectiondao.getAssignedFaculty(section.getId());
            EmailController eController = new EmailController();
           // eController.sendEmail(facultyEntity.getEmail(), "Class Removal" , "The class " + className + " is removed");
            
             
            
        }        
        classdao.removeClass(classEntity.getId());
       
    }
    
    public void removeClass(Long id){
        ClassEntity classEntity = classdao.get(id);
        
        Iterator<SectionEntity> sectionsIterator = classEntity.getSections().iterator();
        
        while(sectionsIterator.hasNext())
        {
            SectionEntity section = sectionsIterator.next();
            List<Long> enrolledCustomersIds = enrollmentdao.getEnrolledCustomersIds(section.getId());            
            Iterator<Long> enrolledCustomersIdsIterator = enrolledCustomersIds.iterator();
            while(enrolledCustomersIdsIterator.hasNext()){
                Long id2 = enrolledCustomersIdsIterator.next();
                
                CustomerEntity customerEntity = customerdao.getCustomer(id2);
                
                EmailController eController = new EmailController();
                //eController.sendEmail(customerEntity.getEmail(), "Class Removal" , "The class " + classEntity.getClassName() + " is removed");
                
            }
            
            FacultyEntity facultyEntity = sectiondao.getAssignedFaculty(section.getId());
            EmailController eController = new EmailController();
            //eController.sendEmail(facultyEntity.getEmail(), "Class Removal" , "The class " + classEntity.getClassName() + " is removed");
            
                        
        }
        
        classdao.removeClass(classEntity.getId());
       
    }
    
    
}
