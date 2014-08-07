package YogaStudio.service;

import YogaStudio.dao.generic.GenericDao;
import YogaStudio.domain.DomainObject;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericServiceImpl<T extends DomainObject> implements GenericService<T> {
	@Autowired
	GenericDao<T>		genericDao;

	@Override
	public void create(T domainObject) {
		genericDao.create(domainObject);
		
	}

	@Override
	public void delete(T domainObject) {
		genericDao.delete(domainObject);
		
	}

	@Override
	public T get(int id,Class clazz) {		
		
		return (T) genericDao.get(id, clazz);
	}

	@Override
	public List<T> getAll(Class clazz) {		
		return genericDao.getAll(clazz);
	}
	
	
	public List<T> getAll(Date date,Class clazz) {		
		return genericDao.getAll(clazz);
	}

	@Override
	public void update(T domainObject) {
		genericDao.update(domainObject);
	}

    @Override
    public List<T> getByQuery(String query) {
        return genericDao.getByQuery(query);
    }


}
