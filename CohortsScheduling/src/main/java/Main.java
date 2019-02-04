import java.util.List;

import org.optaplanner.core.api.solver.*;
import CohortsSolverData.CohortSolution; 
import CohortDataClasses.*;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
	
    public static void main(String args[])
    {
    	try {
    	
    		//each course object should have a non empty list of sections and a name
    		//each section object should have all fields initialized
    		List<Course> courseList = new ArrayList<Course>(); 
    		//each cohort object should have a name and non-empty list of ClassRequirements
    		//each class requirement should have all fields initialized
    		List<Cohort> cohortList = new ArrayList<Cohort>();
    		
    		FileReader.readClassFile("CEAS_Course_Offerings_Fall_2018.csv", courseList);
    		FileReader.readClassFile("CAS-STEM_Course_Offerings_Fall_2018.csv", courseList);
    		FileReader.readCohortFile("cohortReqsLarge.csv", cohortList);
    		
    		for(Course c:courseList) {
    			for(Section s:c.getSections())
    				s.setDayBool();
    		}
    		
    		//verifies that a course exists for each ClassRequirement
    		verifyClassesExist(courseList, cohortList);
    		//Alex Write init function
    		CohortSolution solutions[] = initializeSolution(20, cohortList, courseList);
    		System.out.println(solutions[0].getAssignments().size());
    		SolverFactory<CohortSolution> factory = SolverFactory.createFromXmlResource("SolverConfig.xml");
    		Solver<CohortSolution> solver = factory.buildSolver();
    		for(int i = 0; i<20 ;i++){
    			
    			solutions[i] = (CohortSolution)solver.solve(solutions[i]);
    			
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }

	private static void verifyClassesExist(List<Course> courseList, List<Cohort> cohortList) throws Exception {
		// TODO create SchedulingException class
		for(Cohort cohort : cohortList) {
			for(ClassRequirement req : cohort.getRequirements()) {
				boolean found = false;
				for(Course course : courseList) {
					if(course.getName().equals(req.getClassName())) {
						if(course.getSections()!=null && !course.getSections().isEmpty()) {
							found = true;
						}
					}
				}
				if(!found) {
					//TODO change from generic exception to program specific exception
					throw new Exception("No available sections found for class "+ req.getClassName());
				}
			}
		}
	}

	private static CohortSolution[] initializeSolution(int count, List<Cohort> cohorts, List<Course> courses) throws Exception {
		CohortSolution[] problems = new CohortSolution[count];
		List<CohortSectionAssignment> csa = new ArrayList<>();
		for(Cohort coh:cohorts) {
			//Finds the course for every requirement
			for(ClassRequirement req: coh.getRequirements()) {
				int courseIndex = -1;
				for(Course course: courses) {
					if(req.getClassName().equals(course.getName())) {
						courseIndex = courses.indexOf(course);
					}
				}
				if(courseIndex<0) {
					throw new Exception("Missing Course Object for "+req.getClassName());
				}
				CohortSectionAssignment toAdd = new CohortSectionAssignment();
				toAdd.setMyCohort(coh);
				toAdd.setMyCourse(courses.get(courseIndex));
				toAdd.setSectionCode(req.getSectionsAllowed());
				toAdd.setSeatsNeeded(req.getSeatsNeeded());
				csa.add(toAdd);
			}
		}
		for(int i = 0; i < count; i++) {
			problems[i] = initProblem(i,csa, courses);
			
		}
		return problems;
	}

	private static CohortSolution initProblem(int i, List<CohortSectionAssignment> csa, List<Course> courses) {
		int j = i-1;
		for(CohortSectionAssignment c:csa) {
			c.setAssignment(c.possibleSections().get(j%c.possibleSections().size()));
			j++;
		}
		CohortSolution sol = new CohortSolution();
		sol.setAssignments(csa);
		sol.setCourses(courses);
		return sol;
	}
}
