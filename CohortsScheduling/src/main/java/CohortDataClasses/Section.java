package CohortDataClasses;

import java.util.Date;

public class Section {

	private String name;
	private int    seats; 
	private int    crn; 
	private int    enrolled;
	private String sectionId;
	private int section;
	private String link;
	//start time and end time must be on the same day for ALL section objects
	private Date   startTime;
	private Date   endTime;
	private String daysOfWeek;
	private String instructor;
	private String building;
	private String room; 


	private int    subSectionId;
	
	public Section() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getCrn() {
		return crn;
	}

	public void setCrn(int crn) {
		this.crn = crn;
	}

	public int getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(int enrolled) {
		this.enrolled = enrolled;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public int getSubSectionId() {
		return subSectionId;
	}

	public void setSubSectionId(int subSectionId) {
		this.subSectionId = subSectionId;
	}
	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

}
