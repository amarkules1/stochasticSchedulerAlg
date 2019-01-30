package CohortDataClasses;

import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Course implements Serializable {
	
	private List<Section> sections;
	private String campus;
	private String courseID;
	private int    crn;
	private String name; 
	private String link; 
	private String scheduleType;
	private String startDate;
	private String meetingDay;
	private String meetingTime;
	private String building;
	private String room;
	private String instructor;
	private int    cap;
	private int    unitSize;
	
	public Course() {
		this.sections = new ArrayList<Section>();
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getCrn() {
		return crn;
	}

	public void setCrn(int crn) {
		this.crn = crn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getMeetingDay() {
		return meetingDay;
	}

	public void setMeetingDay(String meetingDay) {
		this.meetingDay = meetingDay;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public int getUnitSize() {
		return unitSize;
	}

	public void setUnitSize(int unitSize) {
		this.unitSize = unitSize;
	}

}
