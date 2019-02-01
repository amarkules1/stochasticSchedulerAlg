package CohortScoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.*;
import java.util.Calendar;
import CohortDataClasses.Cohort;
import CohortDataClasses.CohortSectionAssignment;
import CohortDataClasses.Section;
import CohortsSolverData.CohortSolution;

public class SoftScoringFunctions {
    public static int scoreSchedule(CohortSolution s){
        int score = 0;
        List<Cohort> cohorts = putAssignmentsInCohorts(s);
        score += assignmentsPastSeven(s);
        score += backToBackToBack(cohorts);
        score += moreThanThreeInADay(cohorts);
        score += tooMuchWaitTime(cohorts);
        return score;
    }

    private static int tooMuchWaitTime(List<Cohort> cohorts) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int moreThanThreeInADay(List<Cohort> cohorts) {
		int score = 0;
		for(Cohort c: cohorts) {
			int[] dayCounts = new int[5];
			for(Section s: c.getClassAssignments()) {
				if(s.onDay(0))
					dayCounts[0]++;
				if(s.onDay(1))
					dayCounts[0]++;
				if(s.onDay(2))
					dayCounts[0]++;
				if(s.onDay(3))
					dayCounts[0]++;
				if(s.onDay(4))
					dayCounts[0]++;
			}
			int tmpScore = 0;
			for(int i = 0; i < 5; i++) {
				if(dayCounts[i] > 3)
					tmpScore += dayCounts[i]-3;
				if(dayCounts[i] > 5) 
					tmpScore += dayCounts[i]-5;
			}
			score += (int)Math.pow(tmpScore, 1.75);
		}
		return score;
	}

	private static int backToBackToBack(List<Cohort> cohorts) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int assignmentsPastSeven(CohortSolution s) {
		int count = 0;
		
		for(CohortSectionAssignment c : s.getAssignments()) {
			if(c.getAssignment().getStartTime().getHour()>=19)
				count++;
		}
		return count;
	}

	private static List<Cohort> putAssignmentsInCohorts(CohortSolution solution) {
		Map<String,List<Section>> sectMap = new HashMap<>();
		for(CohortSectionAssignment csa: solution.getAssignments()) {
			if(sectMap.containsKey(csa.getMyCohort().getName())) {
				List<Section> temp = sectMap.get(csa.getMyCohort().getName());
				temp.add(csa.getAssignment());
				sectMap.put(csa.getMyCohort().getName(),temp);
			}else {
				List<Section> temp = new ArrayList<>();
				temp.add(csa.getAssignment());
				sectMap.put(csa.getMyCohort().getName(), temp);
			}
		}
		List<String> cohortNames = new ArrayList<String>(sectMap.keySet());
		List<Cohort> cohorts = new ArrayList<>();
		for(String name:cohortNames) {
			Cohort coh = new Cohort();
			coh.setName(name);
			coh.setClassAssignments(sectMap.get(name));
			cohorts.add(coh);
		}
		return cohorts;
	}
}
