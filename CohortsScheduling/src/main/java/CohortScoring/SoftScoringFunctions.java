package CohortScoring;

import java.util.List;

import CohortDataClasses.Cohort;
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
		//this method Creates a new Cohort object for each cohort and adds the sections 
		//for each CohortSectionAssignment which has that that cohort
		return null;
	}
}
