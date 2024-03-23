package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.RuleValueResult;

import java.util.function.Predicate;

public class StraightRule extends AbstractPokerRule {
    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> getConsecutiveValuesAndReturnHighestValueCard(hand) != null;
    }

    @Override
    public RuleValueResult evaluate(Hand hand) throws EvaluationException {
        Card.Value value = getConsecutiveValuesAndReturnHighestValueCard(hand);
        if (value == null) {
            throw new EvaluationException("Straight rule not applicable, no straight found.");
        }
        return new RuleValueResult(Hand.Rank.STRAIGHT, value);
    }
}
