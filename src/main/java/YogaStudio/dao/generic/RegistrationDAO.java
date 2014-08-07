/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.UserEntity;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 */
@Transactional
public class RegistrationDAO {
    
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    

    
    
    
    public List<UserEntity> getAll() {
        Query q = sf.getCurrentSession().createQuery("from UserEntity");
        return q.list();
    }

    public void add(UserEntity userentity) {
        sf.getCurrentSession().persist(userentity);
    }

    public UserEntity get(int id) {
        return (UserEntity) sf.getCurrentSession().load(UserEntity.class, id);
    }

    public void update(int userId, UserEntity userentity) {
        
        UserEntity user=get(userId);
        user.setUsername(userentity.getUsername());
        user.setFullname(userentity.getFullname());
        user.setEmail(userentity.getEmail());
    }

    public void delete(int userId) {
        UserEntity u = get(userId);
        sf.getCurrentSession().delete(u);
    }
    
    
}
