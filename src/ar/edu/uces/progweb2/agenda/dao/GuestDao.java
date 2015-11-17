package ar.edu.uces.progweb2.agenda.dao;


import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.model.Guest;

public interface GuestDao extends GenericDao<Guest>{

	public List<Guest> getGuests(Long eventoId);

	public Guest getGuest(FormMeetingDTO eventDTO);

}
