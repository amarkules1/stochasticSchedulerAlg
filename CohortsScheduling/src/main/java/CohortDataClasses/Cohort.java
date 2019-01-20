package main.java.CohortDataClasses;

import java.util.List;

public class Cohort {
	
	private List<Course> requirements;
    private List<Section> enrolledClasses;
    private String name;

	public Cohort() {
		// TODO Auto-generated constructor stub
	}

	public List<Course> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Course> requirements) {
		this.requirements = requirements;
	}

	public List<Section> getEnrolledClasses() {
		return enrolledClasses;
	}

	public void setEnrolledClasses(List<Section> enrolledClasses) {
		this.enrolledClasses = enrolledClasses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
