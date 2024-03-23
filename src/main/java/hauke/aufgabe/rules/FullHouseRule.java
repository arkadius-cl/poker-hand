package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.RuleValueResult;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class FullHouseRule extends AbstractPokerRule {


    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> {
            Map<Card.Value, List<Card>> groupedByValue = groupByValue(hand);
            return groupedByValue.size() == 2 &&
                    groupedByValue.values().stream().anyMatch(cards -> cards.size() == 3) &&
                    groupedByValue.values().stream().anyMatch(cards -> cards.size() == 2);
        };
    }

    @Override
    public RuleValueResult evaluate(Hand hand) throws EvaluationException {
        Map<Card.Value, List<Card>> groupedByValue = groupByValue(hand);
        if(groupedByValue.entrySet().size()!=2){
            throw new EvaluationException("Full house rule not applicable, because there are not two different values in the hand");
        }
        return groupedByValue
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == 3)
                .findFirst()
                .map(entry -> new RuleValueResult(Hand.Rank.FULL_HOUSE, entry.getKey()))
                .orElseThrow();
    }
}
