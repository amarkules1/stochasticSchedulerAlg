package CohortsSolverData;
import CohortDataClasses.Cohort;
import CohortDataClasses.CohortSectionAssignment;
import CohortDataClasses.Course;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;
@PlanningSolution
public class CohortSolution {
    private List<CohortSectionAssignment> assignments;
    private List<Course> courses;
    private HardSoftScore score;

    @PlanningScore
    public HardSoftScore getScore(){
        return score;
    }

    @ProblemFactCollectionProperty
    public List<Course> getCourses(){
        return courses;
    }

    @PlanningEntityCollectionProperty
    public List<CohortSectionAssignment> getCohorts(){
        return assignments;
    }
}
