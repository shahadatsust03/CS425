/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.SectionDAO;
import YogaStudio.dao.generic.SemesterDAO;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.SemesterEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sMazumder
 */
public class SemesterService {
    @Autowired
    private SemesterDAO semesterdao;

    public SemesterService(SemesterDAO semesterdao) {
        this.semesterdao = semesterdao;
    }
    
    public SemesterEntity getSemester(Long id) {        
        return semesterdao.get(id);
    }
    public List<SemesterEntity> getAllSemester() {        
        return semesterdao.getAllSemesters();
    }
}
