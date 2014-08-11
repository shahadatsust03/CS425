/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.dao.generic;
import YogaStudio.domain.FileEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author devika
 */
@Transactional
public class FileDAO {

    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public List<FileEntity> getAll() {
        Query q = sf.getCurrentSession().createQuery("from FileEntity");
        return q.list();
    }

    public void add(FileEntity file) {
        sf.getCurrentSession().persist(file);
    }

    public FileEntity get(Long id) {
        return (FileEntity) sf.getCurrentSession().load(FileEntity.class, id);
    }

    public void delete(Long id) {
        FileEntity file = get(id);
        sf.getCurrentSession().delete(file);
    }

}
