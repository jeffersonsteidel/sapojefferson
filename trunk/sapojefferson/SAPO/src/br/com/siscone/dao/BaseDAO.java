package br.com.siscone.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO {

	public void save(Object objeto);

	public void update(Object objeto);

	public void delete(Object objeto);

	@SuppressWarnings("rawtypes")
	public List list(Class clazz);
	
	@SuppressWarnings("rawtypes")
	public List list(Class clazz, String ordenarPor);

	@SuppressWarnings("rawtypes")
	public List list(Class clazz, int firstResult, int maxResults);

	@SuppressWarnings("rawtypes")
	public List listByExample(Object example);

	@SuppressWarnings("rawtypes")
	public Object getById(Serializable id, Class clazz);
	
	public Object refresh(Object object);
}
