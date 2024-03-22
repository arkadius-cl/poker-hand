package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.EvaluationResult;
import hauke.aufgabe.result.ValueResult;

import java.util.function.Predicate;

public class StraightFlushRule extends AbstractPokerRule {

    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> isOneSuit(hand) && getConsecutiveValuesAndReturnHighestValueCard(hand) != null;
    }

    @Override
    public ValueResult evaluate(Hand hand) throws EvaluationException {
        if (!isOneSuit(hand)) {
            throw new EvaluationException("Straight flush rule not applicable, it contains multiple suits.");
        }
        Card.Value value = getConsecutiveValuesAndReturnHighestValueCard(hand);
        return new ValueResult(Hand.Rank.STRAIGHT_FLUSH, value);
    }
}
