import org.optaplanner.core.api.solver.*;
import CohortsSolverData.CohortSolution; 
import CohortDataClasses.*;
import java.io.FileNotFoundException;
import java.util.*;
import CohortScoring.cohortScoring;
public class Main {
	
    public static void main(String args[])
    {
    	try {
    	
    		//each course object should have a non empty list of sections and a name
    		//each section object should have all fields initialized
    		List<Course> courseList = new ArrayList<Course>(); 
    		
    		FileReader.readCourseExcel("CEAS_Course_Offerings_Fall_2018.csv", courseList);
    		FileReader.readCourseExcel("CAS-STEM_Course_Offerings_Fall_2018.csv", courseList);
    		List<Cohort> cohortList = FileReader.readCohortFile("cohortReqsLarge.csv");
    		
    		for(Course c:courseList) {
    			for(Section s:c.getSections())
    				s.setDayBool();
    		}
    		
    		//verifies that a course exists for each ClassRequirement
    		verifyClassesExist(courseList, cohortList);
    		//Alex Write init function
    		CohortSolution solutions[] = initializeSolution(1, cohortList, courseList);
    		//recordSolutions(solutions);
    		SolverFactory<CohortSolution> factory = SolverFactory.createFromXmlResource("SolverConfig.xml");
    		Solver<CohortSolution> solver = factory.buildSolver();
    		cohortScoring score = new cohortScoring();
    		for(int i = 0; i<1 ;i++){
    			System.out.println("Before: " + score.calculateScore(solutions[i]));
    			solutions[i] = (CohortSolution)solver.solve(solutions[i]);
    			System.out.println("After: " + score.calculateScore(solutions[i]));
    		}
    		recordSolutions(solutions);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }

	private static void recordSolutions(CohortSolution[] solutions) {
		for(CohortSolution s: solutions) {
			System.out.println(s.getAssignments().get(0).getAssignment().getName());
			System.out.println("\nSolution");
			List<Cohort> cohorts = putAssignmentsInCohorts(s);
			for(Cohort c: cohorts) {
				System.out.println("Cohort "+c.getName()+" classes:");
				for(Section sect:c.getClassAssignments()) {
					if(sect.getStartTime()!=null) {
						System.out.println("Class: "+sect.getName()
						+" Section: "+sect.getSectionId()
						+" Days: "+sect.getDaysOfWeek()
						+" Time: "+ sect.getStartTime().toString()
						+" - "+sect.getEndTime().toString());
					}else {
						System.out.println("Class: "+sect.getName()
						+" Section: "+sect.getSectionId()
						+" Days: "+sect.getDaysOfWeek()
						+" ONLINE");
					}
						
				}
			}
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
		int j = i;
		for(CohortSectionAssignment c:csa) {
			c.setAssignment(c.possibleSections().get(j%c.possibleSections().size()));
			j++;
		}
		CohortSolution sol = new CohortSolution();
		sol.setAssignments(csa);
		sol.setCourses(courses);
		return sol;
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
