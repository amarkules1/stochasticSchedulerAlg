import java.util.List;

import org.optaplanner.core.api.solver.*;
import CohortsSolverData.CohortSolution; 
import CohortDataClasses.*;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
	
    public static void main() throws FileNotFoundException
    {

    	FileReader fileReader = new FileReader();
    	List<Course> courseList = new ArrayList<Course>(); 
    	List<Cohort> cohortList = new ArrayList<Cohort>();
    	
    	courseList = fileReader.readClassFile("fileName", courseList); 
    	cohortList = fileReader.readCohortFile("fileName", cohortList);
    	//Alex Write init function
    	CohortSolution solutions[] = initializeSolution(20, cohortList, courseList);

    	SolverFactory<CohortSolution> factory = SolverFactory.createFromXmlResource("SolverConfig.xml");
    	Solver<CohortSolution> solver = factory.buildSolver();
    	
    	for(int i = 0; i<20 ;i++){
    		CohortSolution BestSolution = new CohortSolution();
        	BestSolution = (CohortSolution)solver.solve(BestSolution);
        	solutions[i] = BestSolution;
    	}
    	
    	
    }

	private static CohortSolution[] initializeSolution(int count, List<Cohort> cohorts, List<Course> courses) {
		// TODO Auto-generated method stub
		return null;
	}
}
