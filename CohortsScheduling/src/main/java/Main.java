import org.optaplanner.core.api.solver.*;
import CohortsSolverData.CohortSolution;
public class Main {
    public static void main()
    {
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
