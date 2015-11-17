package ar.edu.uces.progweb2.agenda.dao;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.model.Guest;
import ar.edu.uces.progweb2.agenda.model.Meeting;
import ar.edu.uces.progweb2.agenda.model.PrivateEvent;
import ar.edu.uces.progweb2.agenda.model.User;

public interface EventDao extends GenericDao<Event>{

	public List<Meeting> getMeetings(Date date, User user);
	public List<PrivateEvent> getPrivateEvents(Date date, User user);
	public List<Guest> getGuests(String[] args);

}
