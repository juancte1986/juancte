package ar.edu.uces.progweb2.agenda.daoImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.agenda.dao.UserDao;
import ar.edu.uces.progweb2.agenda.dto.FormLoginDTO;
import ar.edu.uces.progweb2.agenda.model.Guest;
import ar.edu.uces.progweb2.agenda.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
	
	private static final int MAX_RESULT = 5;
	
	@Override
	public User getUser(FormLoginDTO form) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class, "u");
		Criterion result = Restrictions.and(Restrictions.eq("u.user", form.getUser()), Restrictions.eq("u.password", form.getPassword()));
		return (User) criteria.add(result).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(String filter) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class, "u");
		Criterion resultName = Restrictions.like("u.name", filter + "%");
		Criterion resultUser = Restrictions.like("u.user", filter + "%");
		Criterion resultSurname = Restrictions.like("u.surname", filter + "%");
		criteria.add(Restrictions.or(resultName, resultUser, resultSurname));
	    criteria.setMaxResults(MAX_RESULT);
		return (List<User>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(List<Long> args) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class, "u");
		Criterion results =  Restrictions.in("u.id", args);
		return (List<User>) criteria.add(results).list();
	}

}
