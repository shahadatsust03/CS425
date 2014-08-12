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
import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.SectionEntity;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sMazumder
 */
public class SectionService {
     @Autowired
    private ClassDAO classdao;
    
    @Autowired
    private EnrollmentDAO enrollmentdao;
    
    @Autowired
    private CustomerDAO customerdao;
    
    @Autowired
    private SectionDAO sectiondao;

    public SectionService(ClassDAO classdao, EnrollmentDAO enrollmentdao, CustomerDAO customerdao, SectionDAO sectiondao) {
        this.classdao = classdao;
        this.enrollmentdao = enrollmentdao;
        this.customerdao = customerdao;
        this.sectiondao = sectiondao;
    }

    public SectionService() {
    }

    public boolean saveSection(SectionEntity sectionEntity) {
       return sectiondao.saveSection(sectionEntity);
    }
    
    public ClassDAO getClassdao() {
        return classdao;
    }
    
    public List<SectionEntity> getAllSections() {        
        return sectiondao.getAllSections();
    }
    
     public List<SectionEntity> getAllSections(Long semesterId) {        
        return sectiondao.getAllSections(semesterId);
    }
    
    public SectionEntity getSection(Long id) {        
        return sectiondao.get(id);
    }
    
    public boolean deleteSection(Long id) {        
        return sectiondao.removeSection(id);
    }
    
    public void setClassdao(ClassDAO classdao) {
        this.classdao = classdao;
    }

    public EnrollmentDAO getEnrollmentdao() {
        return enrollmentdao;
    }

    public void setEnrollmentdao(EnrollmentDAO enrollmentdao) {
        this.enrollmentdao = enrollmentdao;
    }

    public CustomerDAO getCustomerdao() {
        return customerdao;
    }

    public void setCustomerdao(CustomerDAO customerdao) {
        this.customerdao = customerdao;
    }

    public SectionDAO getSectiondao() {
        return sectiondao;
    }

    public void setSectiondao(SectionDAO sectiondao) {
        this.sectiondao = sectiondao;
    }
   
    public boolean checkSpace(SectionEntity sectionEntity){
        int classLimit = sectionEntity.getClassLimit();
        Long enrolledCustomersCount = enrollmentdao.getEnrolledCustomersCount(sectionEntity.getId());
        
        if(enrolledCustomersCount < classLimit)
            return true;
        else       
            return false;
    }
    
    public boolean isEnrolled(Long customerId, Long sectionId) {
        return sectiondao.isEnrolled(customerId, sectionId);
    }
   
}
