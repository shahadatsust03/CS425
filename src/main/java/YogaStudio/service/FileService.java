/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.FileDAO;
import YogaStudio.domain.FileEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eTunkara
 */
public class FileService {
   
    @Autowired
    private FileDAO fileDao;
      
    public FileService(){}
    
    public FileService(FileDAO fileDao){
        this.fileDao= fileDao;      
    }
    
    public List<FileEntity> getAll() {
        return fileDao.getAll();     
    }

    public FileEntity get(Long id) {     
        return fileDao.get(id);      
    }
}
