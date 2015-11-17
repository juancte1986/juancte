package ar.edu.uces.progweb2.agenda.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.agenda.dao.EventDao;
import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.model.Guest;
import ar.edu.uces.progweb2.agenda.model.Meeting;
import ar.edu.uces.progweb2.agenda.model.PrivateEvent;
import ar.edu.uces.progweb2.agenda.model.User;

@Repository("eventDao")
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Meeting> getMeetings(Date date, User user) {
		Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Meeting.class,"m");
		Criterion resultDate = Restrictions.eq("m.date", date);
		Criterion resultOwner = Restrictions.eq("m.owner.id", user.getId());
		criteria.createAlias("guests", "g");
		Criterion resultGuest = Restrictions.eq("g.user.id", user.getId());
		Criterion resultOwnerOrGuest = Restrictions.or(resultOwner, resultGuest);
		return (List<Meeting>) criteria.add(Restrictions.and(resultDate, Restrictions.or(resultOwnerOrGuest))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrivateEvent> getPrivateEvents(Date date, User user) {
		Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PrivateEvent.class,"p");
		Criterion resultDate = Restrictions.eq("p.date", date);
		Criterion resultOwner = Restrictions.eq("p.owner.id", user.getId());
		return (List<PrivateEvent>) criteria.add(Restrictions.and(resultDate, resultOwner)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> getGuests(String[] args) {
		Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Guest.class,"g");
		criteria.createAlias("user", "u");
		Criterion result = Restrictions.in("u.id", args);
		return (List<Guest>) criteria.add(result).list();
	}

}
