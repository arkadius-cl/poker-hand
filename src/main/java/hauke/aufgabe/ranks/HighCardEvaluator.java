package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.rules.EvaluationResult;
import hauke.aufgabe.util.EvaluationResultUtils;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class HighCardEvaluator implements RankEvaluator {

    @Override
    public String evaluate(List<EvaluationResult> handResults) {
        List<String> winners = List.of();
        for (int i = 0; i < 5; i++) {
            winners = findWinnersForIndex(handResults, i);
            if (winners.size() == 1) {
                return winners.getFirst();
            }
        }
        return String.join(", ", winners);
    }

    private List<String> findWinnersForIndex(List<EvaluationResult> handResults, int index) {
        Card.Value highestCard = EvaluationResultUtils.getHighestCardForIndex(handResults, EvaluationResult::values, index);

        return handResults.stream()
                .filter(handResult -> handResult.values().get(index) == highestCard)
                .map(evalResult -> evalResult.hand().getPlayerName())
                .collect(Collectors.toList());
    }


}
