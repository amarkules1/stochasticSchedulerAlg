package CohortDataClasses;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

public class CohortSectionAssignment {

	private Cohort myCohort;
	
	@PlanningVariable(valueRangeProviderRefs = {"courseSection"}, nullable = true)
	private Section assignment;
	
	private Course myCourse;
	
	private String sectionCode;
	
	public CohortSectionAssignment(Course course, Section assignment, Cohort cohort) {
		this.myCohort = cohort;
		this.assignment = assignment;
		this.myCourse = course;
	}

	public Cohort getMyCohort() {
		return myCohort;
	}

	public void setMyCohort(Cohort myCohort) {
		this.myCohort = myCohort;
	}

	public Section getAssignment() {
		return assignment;
	}

	public void setAssignment(Section assignment) {
		this.assignment = assignment;
	}

	public Course getMyCourse() {
		return myCourse;
	}

	public void setMyCourse(Course myCourse) {
		this.myCourse = myCourse;
	}

	@ValueRangeProvider(id = "courseSection")
	public List<Section> possibleSections(){
		List<Section> viable = new ArrayList<>();
		for(Section sect : this.myCourse.getSections()) {
			if(startsWith(sect.getSections(),this.sectionCode)) {
				viable.add(sect);
			}
		}
		return viable;
	}
	
	public static boolean startsWith(String sect, String code) {
		char a = sect.charAt(0);
		int i = 1;
		while(Character.isLetter(a)) {
			if(code.length()<i||a!=code.charAt(i-1)) {
				return false;
			}
		}
		return true;
	}
}
