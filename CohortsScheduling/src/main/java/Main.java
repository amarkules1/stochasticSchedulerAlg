import java.util.List;

import org.optaplanner.core.api.solver.*;
import CohortsSolverData.CohortSolution; 
import CohortDataClasses.*;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
	
    public static void main()
    {
    	try {
    		FileReader fileReader = new FileReader();
    		//each course object should have a non empty list of sections and a name
    		//each section object should have all fields initialized
    		List<Course> courseList = new ArrayList<Course>(); 
    		//each cohort object should have a name and non-empty list of ClassRequirements
    		//each class requirement should have all fields initialized
    		List<Cohort> cohortList = new ArrayList<Cohort>();

    		courseList = fileReader.readClassFile("fileName", courseList); 
    		cohortList = fileReader.readCohortFile("fileName", cohortList);
    		//verifies that a course exists for each ClassRequirement
    		verifyClassesExist(courseList, cohortList);
    		//Alex Write init function
    		CohortSolution solutions[] = initializeSolution(20, cohortList, courseList);

    		SolverFactory<CohortSolution> factory = SolverFactory.createFromXmlResource("SolverConfig.xml");
    		Solver<CohortSolution> solver = factory.buildSolver();
    		for(int i = 0; i<20 ;i++){
    			CohortSolution BestSolution = new CohortSolution();
    			BestSolution = (CohortSolution)solver.solve(BestSolution);
    			solutions[i] = BestSolution;
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
		courses = breakDownCourses(courses, cohorts);
		List<CohortSectionAssignment> csa = new ArrayList<>();
		for(Cohort coh:cohorts) {
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
				int ct = req.getSeatsNeeded()/courses.get(courseIndex).getUnitSize();
				for(int i = 0; i < ct; i++) {
					CohortSectionAssignment toAdd = new CohortSectionAssignment();
					toAdd.setMyCohort(coh);
					toAdd.setMyCourse(courses.get(courseIndex));
					toAdd.setSectionCode(req.getSectionsAllowed());
				}
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

	private static List<Course> breakDownCourses(List<Course> courses, List<Cohort> cohorts) {
		int index = 0;
		for(Course course : courses) {
			List<Integer> sizes = new ArrayList<>();
			for(Cohort cohort:cohorts) {
				for(ClassRequirement req:cohort.getRequirements()) {
					if(req.getClassName().equals(course.getName()))
						sizes.add(req.getSeatsNeeded());
				}
			}
			if(sizes.isEmpty()) {
				courses.remove(index);
			}else {
				course.setUnitSize(findGreatestCommonMultiple(sizes));
				index++;
				course.setSections(breakDownSections(course));
			}
		}
		return courses;
	}

	private static List<Section> breakDownSections(Course course) {
		List<Section> newSections = new ArrayList<>();
		for(Section section: course.getSections()) {
			int numberOfSubSections = (section.getSeats() - section.getEnrolled())/course.getUnitSize();
			for(int i = 0; i < numberOfSubSections; i++) {
				Section newSect = new Section();
				newSect.setDaysOfWeek(section.getDaysOfWeek());
				newSect.setEndTime(section.getEndTime());
				newSect.setName(section.getName());
				newSect.setEnrolled(0);
				newSect.setSeats(course.getUnitSize());
				newSect.setSectionId(section.getSectionId());
				newSect.setStartTime(section.getStartTime());
				newSect.setSubSectionId(i);
				newSections.add(newSect);
			}
		}
		
		return newSections;
	}

	private static int findGreatestCommonMultiple(List<Integer> sizes) {
		Integer min = new Integer(99);
		for(Integer i : sizes) {
			if(i<min) {
				min=i;
			}
		}
		int gcm = 1;
		for(int i = 1; i < min; i++) {
			boolean divByAll = true;
			for(Integer size:sizes) {
				if(size % i !=0) {
					divByAll = false;
				}
			}
			if(divByAll) {
				gcm = i;
			}
		}
		return gcm;
	}
}
