package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.result.HandEvaluationResult;
import hauke.aufgabe.result.RuleValuesListResult;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class HighCardEvaluator implements RankEvaluator {

    @Override
    public String evaluate(List<HandEvaluationResult> handResults) {
        List<String> winners = List.of();
        for (int i = 0; i < 5; i++) {
            winners = findWinnersForIndex(handResults, i);
            if (winners.size() == 1) {
                return winners.getFirst();
            }
        }
        return String.join(", ", winners);
    }

    private Card.Value getHighestCardForIndex(List<HandEvaluationResult> handResults, int index) {
        return handResults.stream()
                .map(element -> ((RuleValuesListResult) element.result()).value().get(index))
                .max(Comparator.comparingInt(Card.Value::ordinal))
                .orElse(null);
    }

    private List<String> findWinnersForIndex(List<HandEvaluationResult> handResults, int index) {
        Card.Value highestCard = getHighestCardForIndex(handResults, index);

        return handResults.stream()
                .filter(handResult -> ((RuleValuesListResult) handResult.result()).value().get(index) == highestCard)
                .map(HandEvaluationResult::name)
                .collect(Collectors.toList());
    }


}
