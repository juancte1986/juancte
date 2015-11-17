package ar.edu.uces.progweb2.agenda.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.uces.progweb2.agenda.bo.EventBO;
import ar.edu.uces.progweb2.agenda.dto.FormDragEventDTO;
import ar.edu.uces.progweb2.agenda.dto.DargEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.exception.BackendException;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.service.EventService;

@Service("eventService")
public class EventServiceImpl implements EventService {
	
	private EventBO eventBO;
	
	@Autowired
	public void setEventBO(EventBO eventBO) {
		this.eventBO = eventBO;
	}

	@Override
	public void saveMeeting(FormMeetingDTO formMeetingDTO) {
		this.eventBO.saveMeeting(formMeetingDTO);
	}

	@Override
	public void savePrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		this.eventBO.savePrivateEvent(formPrivateEventDTO);
	}

	@Override
	public FormPrivateEventDTO getFormPrivateEventDTO(Long id) {
		return this.eventBO.getFormPrivateEventDTO(id);
	}
	
	@Override
	public FormMeetingDTO getFormMeetingDTO(Long id, User user) {
		return this.eventBO.getFormMeetingDTO(id, user);
	}

	@Override
	public void updateMeeting(FormMeetingDTO eventDTO) {
		this.eventBO.updateMeeting(eventDTO);
	}

	@Override
	public void updatePrivateEvent(FormPrivateEventDTO eventDTO) {
		this.eventBO.updatePrivateEvent(eventDTO);
	}

	@Override
	public List<DargEventDTO> getEvents(Date date, User user) {
		return this.eventBO.getEvents(date, user);
	}

	@Override
	public void update(FormDragEventDTO drag) throws BackendException{
		this.eventBO.update(drag);
	}

	@Override
	public void delete(Long id) {
		this.eventBO.delete(id);
	}

	@Override
	public void setConfirmGuest(FormMeetingDTO eventDTO) {
		this.eventBO.setConfirmGuest(eventDTO);
	}
}
