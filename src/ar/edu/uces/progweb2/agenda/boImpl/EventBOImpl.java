package ar.edu.uces.progweb2.agenda.boImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.bo.EventBO;
import ar.edu.uces.progweb2.agenda.dao.EventDao;
import ar.edu.uces.progweb2.agenda.dao.GuestDao;
import ar.edu.uces.progweb2.agenda.dto.FormDragEventDTO;
import ar.edu.uces.progweb2.agenda.dto.DargEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.exception.BackendException;
import ar.edu.uces.progweb2.agenda.model.Guest;
import ar.edu.uces.progweb2.agenda.model.Meeting;
import ar.edu.uces.progweb2.agenda.model.PrivateEvent;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.transformer.EventTransformer;
import ar.edu.uces.progweb2.agenda.utils.EventUtils;

@Component("eventBO")
public class EventBOImpl implements EventBO{
	
	private EventDao eventDao;
	private EventTransformer transformer;
	private GuestDao guestDao;

	@Autowired
	public void setGuestDao(GuestDao guestDao) {
		this.guestDao = guestDao;
	}

	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Autowired
	public void setTransformer(EventTransformer transformer) {
		this.transformer = transformer;
	}

	@Override
	public List<DargEventDTO> getEvents(Date date, User user) {
		List<DargEventDTO> eventsDtos = new ArrayList<DargEventDTO>();
		List<Meeting> eventsMeeting = this.eventDao.getMeetings(date, user);
		List<PrivateEvent> privateEvents = this.eventDao.getPrivateEvents(date, user);	
		for(Meeting meeting : eventsMeeting){
			DargEventDTO eventDTO =  this.transformer.tranformToDragEventDTO(meeting, user);
			eventDTO.setIndex(EventUtils.getIndex(eventsDtos, eventDTO.getStartTime()));
			eventsDtos.add(eventDTO);
		}
		for(PrivateEvent privateEvent : privateEvents){
			DargEventDTO eventDTO =  this.transformer.tranformToDragEventDTO(privateEvent);
			eventDTO.setIndex(EventUtils.getIndex(eventsDtos, eventDTO.getStartTime()));
			eventsDtos.add(eventDTO);
		}
		return eventsDtos;
	}

	@Override
	public void saveMeeting(FormMeetingDTO formMeetingDTO) {
		this.eventDao.save(this.transformer.tranformToMeeting(formMeetingDTO));
	}

	@Override
	public void savePrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		PrivateEvent event = (PrivateEvent) this.transformer.tranformToPrivateEvent(formPrivateEventDTO);
		this.eventDao.save(event);
	}

	@Override
	public FormPrivateEventDTO getFormPrivateEventDTO(Long id) {
		PrivateEvent privateEvent = (PrivateEvent) this.eventDao.getById(id);
		return this.transformer.tranformToFormPrivateEventDTO(privateEvent);
	}

	@Override
	public FormMeetingDTO getFormMeetingDTO(Long id, User user) {
		Meeting meeting = (Meeting) this.eventDao.getById(id);
		return this.transformer.tranformToFormMeetingDTO(meeting, user);
	}

	@Override
	public void updateMeeting(FormMeetingDTO formMeetingDTO) {
		this.eventDao.update(this.transformer.tranformToMeeting(formMeetingDTO));
	}

	@Override
	public void updatePrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		this.eventDao.update(this.transformer.tranformToPrivateEvent(formPrivateEventDTO));
	}

	@Override
	public void update(FormDragEventDTO drag) throws BackendException {
		try{
			this.eventDao.update(this.transformer.tranformToEvent(drag));
		} catch(HibernateException e){
			throw new BackendException(e);
		}
	}

	@Override
	public void delete(Long id) {
		this.eventDao.delete(this.eventDao.getById(id));
	}

	@Override
	public void setConfirmGuest(FormMeetingDTO eventDTO) {
		Guest guest = this.guestDao.getGuest(eventDTO);
		guest.setConfirmMeeting(eventDTO.getConfirmMeeting());
		this.guestDao.update(guest);
		
	}
	
//	private List<Guest> getGuests(String eliminateGuests){
//		String [] args = eliminateGuests.split(",");
//		List<Guest> guests = new ArrayList<Guest>();
//		this.eventDao.getGuests(args);
//		return guests;
//	}

}
