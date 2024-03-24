package hauke.aufgabe.ranks;

import hauke.aufgabe.rules.EvaluationResult;
import hauke.aufgabe.util.EvaluationResultUtils;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class HighCardEvaluator implements RankEvaluator {

    @Override
    public String evaluate(List<EvaluationResult> handResults) {
        return EvaluationResultUtils.findHighestValueWinner(handResults);
    }

}
