package ar.edu.uces.progweb2.agenda.model;

public class Guest {
	
	private Long id;
	private User user; 
	private Meeting meeting;
	private boolean confirm = false;
	
	public Guest(){
		
	}
	
	public boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
