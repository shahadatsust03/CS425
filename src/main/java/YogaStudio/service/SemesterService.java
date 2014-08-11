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
import YogaStudio.dao.generic.SemesterDAO;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.SemesterEntity;
import YogaStudio.domain.UserEntity;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Shahadat
 */
public class SemesterService {
    
    @Autowired
    private EnrollmentDAO enrollmentdao;
    
    @Autowired
    private CustomerDAO customerdao;
    
    @Autowired
    private SectionDAO sectiondao;
    
    @Autowired
    private SemesterDAO semesterdao;
    
    
    public SemesterService(){}
    
    public SemesterService(EnrollmentDAO enrollmentdao, CustomerDAO customerdao, SectionDAO sectiondao, SemesterDAO semesterdao){
    
        this.enrollmentdao = enrollmentdao;
        this.customerdao = customerdao;
        this.sectiondao = sectiondao;
        this.semesterdao = semesterdao;
        
    }
    
    public SemesterDAO getSemesterdao() {
        return semesterdao;
    }

    public void setSemesterdao(SemesterDAO semeterdao) {
        this.semesterdao = semesterdao;
    }
    
     public List<SemesterEntity> getSemesterList(){
        return semesterdao.getSemesterList();
    }
    public boolean saveSemester(SemesterEntity semesterEntity) {
       return semesterdao.saveSemester(semesterEntity);
    }
    
    public boolean updateSemester(SemesterEntity semesterEntity) {
       return semesterdao.updateSemester(semesterEntity);
    }
    
    public SemesterEntity getSemester(String semesterName, String startDate, String endDate) throws ParseException{
        return semesterdao.getSemester(semesterName,startDate,endDate);
    }
    
    public SemesterEntity getSemester(Long id){
        return semesterdao.get(id);
    }
    
    public void updateSemester(Long semesterId, SemesterEntity semesterEntity) {
        semesterdao.updateSemester(semesterId, semesterEntity);
    }
    
    public void removeSemester(String semesterName, String startDate, String endDate) throws ParseException{
        SemesterEntity semesterEntity = semesterdao.getSemester(semesterName, startDate, endDate);
        
        Iterator<SectionEntity> sectionsIterator = semesterEntity.getSections().iterator();
        
        while(sectionsIterator.hasNext())
        {
            SectionEntity section = sectionsIterator.next();
            List<Long> enrolledCustomersIds = enrollmentdao.getEnrolledCustomersIds(section.getId());            
            Iterator<Long> enrolledCustomersIdsIterator = enrolledCustomersIds.iterator();
            while(enrolledCustomersIdsIterator.hasNext()){
                Long id = enrolledCustomersIdsIterator.next();
                
                CustomerEntity customerEntity = customerdao.getCustomer(id);
                
                EmailController eController = new EmailController();
                //eController.sendEmail(customerEntity.getEmail(), "Semester Removal" , "The section " + section.getSectionName() + " is removed");
                
            }
            
            FacultyEntity facultyEntity = sectiondao.getAssignedFaculty(section.getId());
            EmailController eController = new EmailController();
            //eController.sendEmail(facultyEntity.getEmail(), "Semester Removal" , "The section " + section.getSectionName() + " is removed");
            
             
            
        }
        
        semesterdao.removeSemester(semesterEntity.getId());
       
    }
    
    public void removeSemester(Long id) throws ParseException{
        SemesterEntity semesterEntity = semesterdao.get(id);
        
        Iterator<SectionEntity> sectionsIterator = semesterEntity.getSections().iterator();
        
        while(sectionsIterator.hasNext())
        {
            SectionEntity section = sectionsIterator.next();
            List<Long> enrolledCustomersIds = enrollmentdao.getEnrolledCustomersIds(section.getId());            
            Iterator<Long> enrolledCustomersIdsIterator = enrolledCustomersIds.iterator();
            while(enrolledCustomersIdsIterator.hasNext()){
                Long id2 = enrolledCustomersIdsIterator.next();
                
                CustomerEntity customerEntity = customerdao.getCustomer(id2);
                
                EmailController eController = new EmailController();
                //eController.sendEmail(customerEntity.getEmail(), "Semester Removal" , "The section " + section.getSectionName() + " is removed");
                
            }
            
            FacultyEntity facultyEntity = sectiondao.getAssignedFaculty(section.getId());
            EmailController eController = new EmailController();
            //eController.sendEmail(facultyEntity.getEmail(), "Semester Removal" , "The section " + section.getSectionName() + " is removed");
            
             
            
        }
        
        semesterdao.removeSemester(semesterEntity.getId());
       
    }
    
}
