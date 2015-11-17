package ar.edu.uces.progweb2.agenda.dto;


public class FormCalendarDTO {

	private String date;
	private int actualPage; // actual page solo puede tomar valores 0, 1 y -1

	public FormCalendarDTO() {

	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getActualPage() {
		return actualPage;
	}

	public void setActualPage(int actualPage) {
		this.actualPage = actualPage;
	}

}
