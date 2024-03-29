package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.util.CardUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class FullHouseRule implements EvaluationRule {


    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> {
            Map<Card.Value, List<Card>> groupedByValue = CardUtils.groupByValue(hand.getCards());
            return groupedByValue.size() == 2 &&
                    groupedByValue.values().stream().anyMatch(cards -> cards.size() == 3) &&
                    groupedByValue.values().stream().anyMatch(cards -> cards.size() == 2);
        };
    }

    @Override
    public EvaluationResult evaluate(Hand hand) throws EvaluationException {
        Map<Card.Value, List<Card>> groupedByValue = CardUtils.groupByValue(hand.getCards());
        if (groupedByValue.entrySet().size() != 2) {
            throw new EvaluationException("Full house rule not applicable, because there are not two different values in the hand");
        }
        return groupedByValue
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == 3)
                .findFirst()
                .map(entry -> new EvaluationResult(Hand.Rank.FULL_HOUSE, hand, List.of(entry.getKey())))
                .orElseThrow();
    }
}
