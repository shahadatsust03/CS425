/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.dao.generic;

import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.UserEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author devika
 */
@Transactional
public class FacultyDAO {

    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public List<FacultyEntity> getAll() {
        Query q = sf.getCurrentSession().createQuery("from FacultyEntity");
        return q.list();
    }

    public void add(FacultyEntity facultyEntity) {
        sf.getCurrentSession().persist(facultyEntity);
    }

    public FacultyEntity get(Long id) {
        return (FacultyEntity) sf.getCurrentSession().load(FacultyEntity.class, id);
    }

    public void update(FacultyEntity facultyEntity) {

        sf.getCurrentSession().update(facultyEntity);
    }

    public void delete(Long facultyId) {
        FacultyEntity faculty = get(facultyId);
        sf.getCurrentSession().delete(faculty);
    }
    // chunming
    public FacultyEntity getNewAdvisor(){
        // Select the faculty that has fewest advisees as advisor
        String hql = "From FacultyEntity FE ORDER BY FE.customers.size ASC";
        Query q = sf.getCurrentSession().createQuery(hql);
        q.setMaxResults(1);

        return (FacultyEntity)q.uniqueResult();
    } 
}
