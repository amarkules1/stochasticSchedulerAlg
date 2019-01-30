package CohortScoring;

import java.util.ArrayList;
import java.util.List;

import CohortDataClasses.Cohort;
import CohortDataClasses.Section;
import CohortsSolverData.CohortSolution;
import CohortDataClasses.CohortSectionAssignment;
public class HardScoringFunctions {
    public static int scoreSchedule(CohortSolution solution){
        int score = 0;
        for(CohortSectionAssignment assignment:solution.) {
        	Section[] sects = s.getEnrolledClasses();
        	score += classConflictsForCohort(sects);
        }
        return score;
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
