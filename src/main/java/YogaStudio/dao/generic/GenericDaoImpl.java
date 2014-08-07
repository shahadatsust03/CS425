package YogaStudio.dao.generic;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import YogaStudio.domain.DomainObject;
import org.springframework.transaction.annotation.Transactional;

@Repository("genericDao")
@Transactional
public class GenericDaoImpl<T extends DomainObject> implements GenericDao<T> {
    @Autowired	
    private SessionFactory		sessionFactory;
    
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
		}	

	@Override
	public void create(T object) {
		currentSession().save(object);
	}

	@Override
	public void update(T object) {
		currentSession().update(object);
	}

	@Override
	public void delete(T object) {
		currentSession().delete(object);
	}

	@Override
	public T get(int id,Class clazz) {
		Long[] p= {1l,1l,1l,1l,1l,1l};

		return (T) currentSession().get(clazz, id);
	}

	@Override
	public List<T> getAll(Class clazz) {
		String query="from "+clazz.getName();
		Query hibernateQuery = currentSession().createQuery(query); 
		return (List<T> )hibernateQuery.list();
	}	

    @Override
    public List<T> getByQuery(String query) {
       Query hibernateQuery = currentSession().createQuery(query); 
		return (List<T> )hibernateQuery.list();
    }
	
}
