package CohortDataClasses;

import java.util.Date;

public class Section {

	private String name;
	private Integer seats;
	private Integer enrolled;
	private String sectionId;
	//start time and end time must be on the same day for ALL section objects
	private Date startTime;
	private Date endTime;
	private String daysOfWeek;
	private int subSectionId;
	
	public Section() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Integer enrolled) {
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

}
