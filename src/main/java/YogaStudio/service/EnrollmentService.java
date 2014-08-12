/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.ClassDAO;
import YogaStudio.dao.generic.CustomerDAO;
import YogaStudio.dao.generic.EnrollmentDAO;
import YogaStudio.dao.generic.SectionDAO;
import YogaStudio.dao.generic.TranscriptDAO;
import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.EnrollmentEntity;
import YogaStudio.domain.SectionEntity;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sMazumder
 */
public class EnrollmentService {
     @Autowired
    private TranscriptDAO transcriptdao;
    
    @Autowired
    private EnrollmentDAO enrollmentdao;
    
    @Autowired
    private CustomerDAO customerdao;
    
    @Autowired
    private SectionDAO sectiondao;

    public EnrollmentService(TranscriptDAO transcriptdao, EnrollmentDAO enrollmentdao, CustomerDAO customerdao, SectionDAO sectiondao) {
        this.transcriptdao = transcriptdao;
        this.enrollmentdao = enrollmentdao;
        this.customerdao = customerdao;
        this.sectiondao = sectiondao;
    }

    public EnrollmentService() {
    }

    public void saveEnrollment(EnrollmentEntity enrollmentEntity) {
       enrollmentdao.saveEnrollment(enrollmentEntity);
    }
    
    //returns true if a prerequisite is met by a customer
    public boolean checkPrerequisites(Long customerId, Long sectionId) throws ParseException{
        
        List<ClassEntity> prerequisitesOfSection = sectiondao.getPrerequisites(sectionId);
        List<ClassEntity> coursesTakenByCustomer = transcriptdao.getListOfCoursesTaken(customerId);
        
        if(prerequisitesOfSection.size() == 0){
            //System.out.println("111111111111111111111111111111111111111111111111111111");
            return true;
        }
        if(coursesTakenByCustomer.size() == 0){
             //System.out.println("2222222222222222222222222222222222222222222222222222222222222");
            return false;
        }
        
        Iterator<ClassEntity> iterator1 = prerequisitesOfSection.iterator();
        
        while(iterator1.hasNext())
        {
            ClassEntity prerequisite = iterator1.next();
            
            boolean currentPrerequisiteMet = false;
            Iterator<ClassEntity> iterator2 = coursesTakenByCustomer.iterator();
            while(iterator2.hasNext()){
                ClassEntity courseTaken = iterator2.next();
                if(prerequisite.getId() == courseTaken.getId()){
                    currentPrerequisiteMet = true;
                    break;
                }
            }
            if(currentPrerequisiteMet == false){
               // System.out.println("33333333333333333333333333333333333333333333333333333333333333");
                return false;
            }
        }
        //System.out.println("4444444444444444444444444444444444444444444444444444444444444444");
        return true;
    }
    
    public EnrollmentEntity getEnrollment(Long customerId, Long sectionId){
        return enrollmentdao.getEnrollment(customerId, sectionId);
    }
    
    public boolean waitingListIsEmpty(Long sectionId){
       return enrollmentdao.waitingListIsEmpty(sectionId);
    }
    
    public CustomerEntity getCustoemrAtFrontOfWaitingList(Long sectionId){
       return enrollmentdao.getCustoemrAtFrontOfWaitingList(sectionId);
    }
    
    public List<SectionEntity> getEnrolledSections(Long customerId){
        return enrollmentdao.getEnrolledSections(customerId);
    }
    
    public void removeEnrollment(EnrollmentEntity enrollmentEntity){
        enrollmentdao.removeEnrollment(enrollmentEntity);
    }

    public List<EnrollmentEntity> getEnrollments(Long id) {
      return  enrollmentdao.getEnrollments(id);
    }
    
    
    
   
}
