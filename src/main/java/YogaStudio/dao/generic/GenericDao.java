package YogaStudio.dao.generic;

import YogaStudio.domain.DomainObject;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

public interface GenericDao<T extends DomainObject> {
	public void create(T object);

	public void update(T object);

	public T get(int id,Class Clazz);

	public List<T> getAll(Class clazz);	
	
	public void delete(T object);	
        
        public List<T> getByQuery(String query);
}
