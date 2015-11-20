package ar.edu.uces.progweb2.agenda.dto;

public class DargEventDTO {

	private String id;
	private String name;
	private String date;
	private String startTime;
	private String endTime;
	private String type;
	private int index;
	private int top;
	private int height;
	private int state;
	private boolean isOwner = false;
	private boolean isGuest = false;

	public String getType() {
		return type;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public boolean getIsGuest() {
		return isGuest;
	}

	public void setIsGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
