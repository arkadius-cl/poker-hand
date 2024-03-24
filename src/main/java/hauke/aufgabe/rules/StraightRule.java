package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.util.CardUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class StraightRule implements EvaluationRule {
    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> CardUtils.getStraightHighestValue(hand.getCards()) != null;
    }

    @Override
    public EvaluationResult evaluate(Hand hand) throws EvaluationException {
        Card.Value value = CardUtils.getStraightHighestValue(hand.getCards());
        if (Objects.isNull(value)) {
            throw new EvaluationException("Straight rule not applicable, no straight found.");
        }
        return new EvaluationResult(Hand.Rank.STRAIGHT, hand, List.of(value));
    }
}
