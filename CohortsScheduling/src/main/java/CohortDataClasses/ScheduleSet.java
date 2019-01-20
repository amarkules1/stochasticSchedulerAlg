package CohortDataClasses;

import org.optaplanner.core.api.domain.entity.PlanningEntity;

@PlanningEntity
public class ScheduleSet {

    private Schedule[] set;

    public Schedule[] getSet()
    {
        return set;
    }
}
