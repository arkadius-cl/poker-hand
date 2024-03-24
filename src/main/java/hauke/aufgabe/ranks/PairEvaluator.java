package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.rules.EvaluationResult;
import hauke.aufgabe.util.EvaluationResultUtils;

import java.util.List;

public class PairEvaluator implements RankEvaluator {


    @Override
    public String evaluate(List<EvaluationResult> handResults) {
        List<Card.Value> allPairs = handResults.stream()
                .map(result -> result.values().getFirst())
                .toList();
        Card.Value highestPair = findHighestPair(allPairs);
        List<EvaluationResult> winners = handResults.stream()
                .filter(handResult -> handResult.values().getFirst() == highestPair)
                .toList();
        if (winners.size() == 1) {
            return winners.getFirst().hand().getPlayerName();
        }
        List<EvaluationResult> resultsWithoutCards = EvaluationResultUtils.removeFromResults(handResults, result -> {
            Card.Value pairValue = findHighestPair(result.values());
            return value -> value != pairValue;
        });
        return EvaluationResultUtils.findHighestValueWinner(resultsWithoutCards);
    }

    private Card.Value findHighestPair(List<Card.Value> cards) {
        return cards.stream().max(Card.Value::compareTo).orElse(null);
    }


}
