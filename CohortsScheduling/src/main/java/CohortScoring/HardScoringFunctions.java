package CohortScoring;

import java.util.ArrayList;
import java.util.List;

import CohortDataClasses.Cohort;
import CohortDataClasses.Course;
import CohortDataClasses.Section;
import CohortsSolverData.CohortSolution;

public class HardScoringFunctions {
    public static int scoreSchedule(CohortSolution solution){
        int score = 0;
        List<Cohort> cohorts = putAssignmentsInCohorts(solution);
        for(Cohort s:cohorts) {
        	Section[] sects = (Section[])s.getClassAssignments().toArray();
        	score += classConflictsForCohort(sects);
        }
        List<Course> courses = addEnrollmentsToCourses(solution);
        score+= calculateOverEnrolledScore(courses);
        return score;
    }

    private static int calculateOverEnrolledScore(List<Course> courses) {
		//this method adds a point for every 5 students over the limit for each section
    	//Ex Sect1:0 students over, Sect2:1 student over, Sect3:9 students over
    	//Sect1 add 0 pt, Sect2 add 1 pt, sect3 add 2 pt
		return 0;
	}

	private static List<Course> addEnrollmentsToCourses(CohortSolution solution) {
		//this method creates a new Course object for each course and increments 
    	//the enrolled field for each new Section object of that course
    	//as indicated by the CohortSectionAssignment List
		return null;
	}

	private static List<Cohort> putAssignmentsInCohorts(CohortSolution solution) {
		//this method Creates a new Cohort object for each cohort and adds the sections 
		//for each CohortSectionAssignment which has that that cohort
		return null;
	}
	
	

	protected static int classConflictsForCohort(Section[] sects) {
    	int score = 0;
    	
    	for(int i = 0; i < sects.length;i++) {
    		for(int j = (1+i); j < sects.length;j++) {
    			if(sameDay(sects[i].getDaysOfWeek(),sects[j].getDaysOfWeek())) {
    				if(sects[i].getEndTime().before(sects[j].getEndTime())&&sects[i].getEndTime().after(sects[j].getStartTime())) {
    					score--;
    				}else if(sects[i].getEndTime().before(sects[j].getEndTime())&&sects[i].getEndTime().after(sects[j].getStartTime())) {
    					score--;
    				}else if(sects[i].getEndTime().compareTo(sects[j].getEndTime())==0 || sects[i].getEndTime().compareTo(sects[j].getStartTime())==0) {
    					score--;
    				}else if(sects[j].getEndTime().compareTo(sects[i].getEndTime())==0 || sects[j].getEndTime().compareTo(sects[i].getStartTime())==0) {
    					score--;
    				}
    			}
    		}
    	}
    	return score;
    }
    
    protected static boolean sameDay(String days1, String days2) {
    	boolean same = false;
    	for(int i = 0; i < days1.length(); i++) {
    		for(int j = 0; j < days2.length(); j++) {
    			if(days1.charAt(i) == days2.charAt(j)) {
    				same = true;
    			}
    		}
    	}
    	return same;
    }
    
    
}
