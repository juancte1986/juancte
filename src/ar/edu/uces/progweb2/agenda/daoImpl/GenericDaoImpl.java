package ar.edu.uces.progweb2.agenda.daoImpl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.agenda.dao.GenericDao;

@Transactional(readOnly = true)
@Repository("genericDao")
public abstract class GenericDaoImpl<T> implements GenericDao<T>{
	
	private Class<T> persistentClass;
	protected SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(this.persistentClass.getName(), id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void delete(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(object);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void save(T object) {
		Session session = sessionFactory.getCurrentSession();
		//session.merge(object);
		session.save(object);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void update(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.update(object);
	}
	
	@Override
	public Long getCount() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(this.persistentClass.getName());
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Session session = sessionFactory.getCurrentSession(); 
		Query query = session.createQuery("FROM "+this.persistentClass.getName());
		return (List<T>) query.list();
	}
	
}
