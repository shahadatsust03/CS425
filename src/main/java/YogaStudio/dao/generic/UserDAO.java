/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.dao.generic;

import YogaStudio.domain.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat User data access class -- this will interact with the
 * database source
 */
@Transactional
public class UserDAO {

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

    public boolean add(UserEntity userentity) {
        try {
            sf.getCurrentSession().persist(userentity);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding new customer " + e.getMessage().toString());
            return false;
        }
    }

    public UserEntity get(int id) {
        return (UserEntity) sf.getCurrentSession().load(UserEntity.class, id);
    }

    public void update(int userId, UserEntity userentity) {
        UserEntity user = get(userId);
        user.setUsername(userentity.getUsername());
        user.setFullname(userentity.getFullname());
        user.setEmail(userentity.getEmail());
    }

    public void delete(int userId) {
        UserEntity u = get(userId);
        sf.getCurrentSession().delete(u);
    }

    public boolean authenticateUser(String username, String password, Byte enabled) {
        Query result = sf.getCurrentSession().getNamedQuery("userLogin");
        result.setParameter("username", username);
        //result.setParameter("email", username);
        result.setParameter("password", password);
        result.setParameter("enabled", enabled);
        if (result.list().isEmpty()) {
            return false;
        }
        return true;
    }

    public List<UserEntity> findBy(String fieldName, String value) {
        List list = null;
        try {
            String query = "from UserEntity u where u.fieldname =:fieldvalue ".replace("fieldname", fieldName);
            Query result = sf.getCurrentSession().createQuery(query);
            result.setParameter("fieldvalue", value);
            list = result.list();
        } catch (Exception e) {
            System.out.print("Error finding user : " + e.getMessage());
        }
        return list;
    }

    public List findEntityBy(String entityName, String fieldName, String value) {
        List list = null;
        try {
            String query = "from entity u where u.fieldname =:fieldvalue ".
                    replace("entity", entityName).
                    replace("fieldname", fieldName);
            Query result = sf.getCurrentSession().createQuery(query);
            result.setParameter("fieldvalue", value);
            list = result.list();
        } catch (Exception e) {
            System.out.print("Error finding user : " + e.getMessage());
        }
        return list;
    }

    public boolean userExists(String fieldName, String value) {
        try {
            List list = findBy(fieldName, value);
            return !list.isEmpty();
        } catch (Exception e) {
            System.out.print("Error user exists : " + e.getMessage());
            return false;
        }
    }

    public UserEntity findUser(String username, String password) {
       // UserEntity u = null;
        Query result = sf.getCurrentSession().getNamedQuery("findUserByUsername");
        result.setParameter("username", username);
        result.setParameter("password", password);
        result.setParameter("enabled", Byte.valueOf("1"));
        //to do
        if (result.list() != null && result.list().size() > 0) {
            return (UserEntity) result.list().get(0);
        }
        return null;
    }
}
