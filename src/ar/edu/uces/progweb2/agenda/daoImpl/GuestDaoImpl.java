package ar.edu.uces.progweb2.agenda.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.agenda.dao.GuestDao;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.model.Guest;


@Repository("guestDao")
public class GuestDaoImpl extends GenericDaoImpl<Guest> implements GuestDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> getGuests(Long eventoId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Guest.class, "g");
		Criterion result = Restrictions.eq("g.meeting.id", eventoId);
		return (List<Guest>) criteria.add(result).list();
	}

	@Override
	public Guest getGuest(FormMeetingDTO eventDTO) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Guest.class, "g");
		Criterion resultEvent = Restrictions.eq("g.meeting.id", eventDTO.getId());
		Criterion resultUser = Restrictions.eq("g.user.id", eventDTO.getUserLogin().getId());
		return (Guest) criteria.add(Restrictions.and(resultEvent, resultUser)).uniqueResult();
	}
	
}
