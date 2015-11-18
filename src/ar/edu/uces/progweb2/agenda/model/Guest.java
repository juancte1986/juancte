package ar.edu.uces.progweb2.agenda.model;

public class Guest {

	private Long id;
	private User user;
	private Meeting meeting;
	private boolean confirmMeeting = false;
	private boolean rejectedMeeting = false;

	public Guest() {

	}

	public boolean getConfirmMeeting() {
		return confirmMeeting;
	}

	public void setConfirmMeeting(boolean confirmMeeting) {
		this.confirmMeeting = confirmMeeting;
	}

	public boolean getRejectedMeeting() {
		return rejectedMeeting;
	}

	public void setRejectedMeeting(boolean rejectedMeeting) {
		this.rejectedMeeting = rejectedMeeting;
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
