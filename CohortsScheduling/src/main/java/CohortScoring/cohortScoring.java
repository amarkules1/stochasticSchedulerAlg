package CohortScoring;

import CohortDataClasses.*;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScoreHolder;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public class cohortScoring implements EasyScoreCalculator {


    public HardSoftScore calculateScore(Object o) {

        int softScore = 0;
        int hardScore = 0;

        for (Schedule s:
                ((ScheduleSet)o).getSet()) {

            int tmp = SoftScoringFunctions.scoreSchedule(s);
            softScore += (tmp * tmp);
            hardScore += HardScoringFunctions.scoreSchedule(s);
        }

        return HardSoftScore.valueOf(hardScore,softScore);
    }
}
