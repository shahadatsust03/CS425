/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

/**
 *
 * @author sMazumder
 */
import YogaStudio.dao.generic.ClassDAO;
import YogaStudio.dao.generic.CustomerDAO;
import YogaStudio.dao.generic.EnrollmentDAO;
import YogaStudio.dao.generic.ScheduleDAO;
import YogaStudio.dao.generic.SectionDAO;
import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.ScheduleEntity;
import YogaStudio.domain.SectionEntity;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleService {
    
    @Autowired
    private ClassDAO classdao;
    
    @Autowired
    private EnrollmentDAO enrollmentdao;
    
    @Autowired
    private CustomerDAO customerdao;
    
    @Autowired
    private SectionDAO sectiondao;

    @Autowired
    private ScheduleDAO scheduledao;
    
    public ScheduleService(ClassDAO classdao, EnrollmentDAO enrollmentdao, CustomerDAO customerdao, SectionDAO sectiondao,ScheduleDAO scheduledao) {
        this.classdao = classdao;
        this.enrollmentdao = enrollmentdao;
        this.customerdao = customerdao;
        this.sectiondao = sectiondao;
        this.scheduledao = scheduledao;
    }
    
    public ScheduleService() {
    }

    public boolean saveSchedule(ScheduleEntity scheduleEntity) {
       return scheduledao.saveSchedule(scheduleEntity);
    }
    
    public List<ScheduleEntity> getAllSchedules() {
       return scheduledao.getAllSchedule();
    }
    
    public List<ScheduleEntity> getAllSchedulesAvailable() {
       return scheduledao.getAllSchedulesAvailable();
    }
    
     public List<ScheduleEntity> getAllSchedulesUsed() {
       return scheduledao.getAllSchedulesUsed();
    }

    public ScheduleEntity getSchedule(Long id) {
        return scheduledao.get(id);
    }
}
