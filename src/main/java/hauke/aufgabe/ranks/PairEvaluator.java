package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.result.HandEvaluationResult;
import hauke.aufgabe.result.RuleValueResult;

import java.util.List;

public class PairEvaluator implements RankEvaluator {

    @Override
    public String evaluate(List<HandEvaluationResult> handResults) {
        List<Card.Value> allPairs = handResults.stream()
                .map(result -> ((RuleValueResult) result.result()).value())
                .toList();
        Card.Value highestPair = findHighestPair(allPairs);
        List<String> winners = handResults.stream()
                .filter(handResult -> ((RuleValueResult) handResult.result()).value() == highestPair)
                .map(HandEvaluationResult::name)
                .toList();
        return winners.size() == 1 ? winners.getFirst() : String.join(", ", winners);
    }

    private Card.Value findHighestPair(List<Card.Value> cards) {
        return cards.stream().max(Card.Value::compareTo).orElse(null);
    }
}
