package ar.edu.uces.progweb2.agenda.model;

import java.util.Set;

public class Meeting extends Event{
	
	private Hall hall ;
	private String theme;
	private Set<Guest> guests;
	
	public Meeting(){
		
	}
	
	public Hall getHall() {
		return hall;
	}



	public void setHall(Hall hall) {
		this.hall = hall;
	}



	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public Set<Guest> getGuests() {
		return guests;
	}
	
	public void setGuests(Set<Guest> guests) {
		this.guests = guests;
	}
}
