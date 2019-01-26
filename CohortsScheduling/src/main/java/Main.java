import java.util.List;

import org.optaplanner.core.api.solver.*;

import CohortDataClasses.Cohort;
import CohortDataClasses.Course;
import CohortsSolverData.CohortSolution;
public class Main {
    public static void main()
    {
    	//Jake add functions to assign these
    	List<Cohort> cohorts= null;
    	List<Course> courses = null;
    	//Alex Write init function
    	CohortSolution solutions[] = initializeSolution(20, cohorts, courses);
    	
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
