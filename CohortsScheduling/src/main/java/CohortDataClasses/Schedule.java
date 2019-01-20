package CohortDataClasses;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;


public class Schedule {
    private Cohort cohort;
    @PlanningVariable(valueRangeProviderRefs = {"requirements"})
    private Section[] enrolledIn;

    public Cohort getCohort(){
        return cohort;
    }

    public Section[] getEnrollments(){
        return enrolledIn;
    }
}
