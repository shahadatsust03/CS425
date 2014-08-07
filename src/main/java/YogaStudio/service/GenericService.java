package YogaStudio.service;

import YogaStudio.domain.DomainObject;
import java.util.List;




public interface GenericService<T extends DomainObject> {
	public void create(T object);

	public void update(T object);

	public T get(int id,Class clazz);
	
	//public T get(String code, Class clazz);
	
	

	public List<T> getAll(Class clazz);
	
	//public List<T> getAll(boolean active,Class clazz);
	
	public void delete(T object);
        public List<T> getByQuery(String query);
	
	//public T get(String[] fields, Object[] params,String operator,Class clazz);
	
	//public List<T> getAll(String[] fields, Object[] params,String operator,Class clazz);
}
