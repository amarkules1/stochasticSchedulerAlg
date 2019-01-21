package CohortScoring;

import CohortDataClasses.*;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public class cohortScoring implements EasyScoreCalculator {


    public HardSoftScore calculateScore(Object o) {
        return HardSoftScore.valueOf(
                HardScoringFunctions.scoreSchedule((Cohort)o),
                SoftScoringFunctions.scoreSchedule((Cohort)o)
        );
    }
}
