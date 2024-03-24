package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.rules.EvaluationResult;

import java.util.List;

public class PairEvaluator implements RankEvaluator {

    @Override
    public String evaluate(List<EvaluationResult> handResults) {
        List<Card.Value> allPairs = handResults.stream()
                .map(result -> result.values().getFirst())
                .toList();
        Card.Value highestPair = findHighestPair(allPairs);
        List<String> winners = handResults.stream()
                .filter(handResult -> handResult.values().getFirst() == highestPair)
                .map(evalResult -> evalResult.hand().getPlayerName())
                .toList();
        return winners.size() == 1 ? winners.getFirst() : String.join(", ", winners);
    }

    private Card.Value findHighestPair(List<Card.Value> cards) {
        return cards.stream().max(Card.Value::compareTo).orElse(null);
    }
}
