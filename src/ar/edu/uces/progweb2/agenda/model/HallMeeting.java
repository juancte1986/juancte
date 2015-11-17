package ar.edu.uces.progweb2.agenda.model;

public class HallMeeting {
	
	private long id;
	private Hall hall;
	
	public HallMeeting(){
		
	}
	
	public HallMeeting(Hall hall){
		this.hall = hall;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
}
