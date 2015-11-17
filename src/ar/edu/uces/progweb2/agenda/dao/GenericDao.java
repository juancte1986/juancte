package ar.edu.uces.progweb2.agenda.dao;

import java.util.List;


public abstract interface GenericDao <T> {

	
	public void save(T object);
	
	public void update(T object);
	
	public Long getCount();

	public T getById(Long id);
	
	public List<T> findAll();
	
	public void delete(T object);
}
