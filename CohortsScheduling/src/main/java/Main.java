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
    	
    	CohortSolution solutions[] = new CohortSolution[20];
    	SolverFactory<CohortSolution> factory = SolverFactory.createFromXmlResource("SolverConfig.xml");
    	Solver<CohortSolution> solver = factory.buildSolver();
    	
    	for(int i = 0; i<20 ;i++){
    		CohortSolution BestSolution = new CohortSolution();
        	BestSolution = (CohortSolution)solver.solve(BestSolution);
        	solutions[i] = BestSolution;
    	}
    	
    	
    }
}
