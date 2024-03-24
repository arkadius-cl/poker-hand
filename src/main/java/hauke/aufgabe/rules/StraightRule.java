package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.RuleValueResult;
import hauke.aufgabe.util.CardUtils;

import java.util.function.Predicate;

public class StraightRule implements EvaluationRule {
    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> CardUtils.getStraightHighestValue(hand.getCards()) != null;
    }

    @Override
    public RuleValueResult evaluate(Hand hand) throws EvaluationException {
        Card.Value value = CardUtils.getStraightHighestValue(hand.getCards());
        if (value == null) {
            throw new EvaluationException("Straight rule not applicable, no straight found.");
        }
        return new RuleValueResult(Hand.Rank.STRAIGHT, value);
    }
}
