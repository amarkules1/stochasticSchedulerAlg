package CohortScoring;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public class cohortScoring implements EasyScoreCalculator {
    public HardSoftScore calculateScore(Object o) {
        int softScore = 0;
        int hardScore = 0;



        return HardSoftScore.valueOf(hardScore,softScore);
    }
}
