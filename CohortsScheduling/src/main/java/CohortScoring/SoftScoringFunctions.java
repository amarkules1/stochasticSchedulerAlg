package CohortScoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		// TODO Auto-generated method stub
		return 0;
	}

	private static int backToBackToBack(List<Cohort> cohorts) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int assignmentsPastSeven(CohortSolution s) {
		// TODO Auto-generated method stub
		return 0;
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
