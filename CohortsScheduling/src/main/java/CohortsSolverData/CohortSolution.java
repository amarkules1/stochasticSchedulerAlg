package CohortsSolverData;
import CohortDataClasses.Cohort;
import CohortDataClasses.Course;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;
@PlanningSolution
public class CohortSolution {
    private int cohortCount;
    private List<Cohort> cohorts;
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
    public List<Cohort> getCohorts(){
        return cohorts;
    }
}
