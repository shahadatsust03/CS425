/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.FacultyDAO;
import YogaStudio.dao.generic.UserDAO;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.UserEntity;
import YogaStudio.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sMazumder
 */
public class FacultyService {
     @Autowired
    private FacultyDAO facultyDao;

    public FacultyService() {
    }

    public FacultyService(FacultyDAO facultyDao) {
        this.facultyDao = facultyDao;
    }

    public FacultyDAO getFacultyDao() {
        return facultyDao;
    }

    public void setFacultyDao(FacultyDAO facultyDao) {
        this.facultyDao = facultyDao;
    }
    
    public boolean add(FacultyEntity facultyEntity) {
        try
        {
            facultyDao.add(facultyEntity);
            return true;
        }  catch(Exception ex){
        }
        return false;
    }
    
    public List<FacultyEntity> getAllFaculty() {        
            return facultyDao.getAll();
    }
    
    public Boolean removeFaculty(Long id) {   
        try
        {
            facultyDao.delete(id);
            return true;
        }catch( Exception ex){
        }
        return false;
    }

    public FacultyEntity getFaculty(Long id) {
        return facultyDao.get(id);
    }
      
}
