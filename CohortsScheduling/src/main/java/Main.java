import org.optaplanner.core.api.solver.*;
import java.io.File;
import CohortsSolverData.CohortSolution;
public class Main {
    public static void main()
    {
    	
    	Solver solver = SolverFactory.createFromXmlResource("SolverConfig.xml").buildSolver();
    	
    	CohortSolution BestSolution = new CohortSolution();
    	
    	BestSolution = (CohortSolution)solver.solve(BestSolution);
    }
}
