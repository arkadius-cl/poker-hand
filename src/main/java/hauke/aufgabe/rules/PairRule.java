package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.util.CardUtils;

import java.util.List;
import java.util.function.Predicate;

public class PairRule implements EvaluationRule {
    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> CardUtils.getValuesByCount(hand.getCards(), 2).size() == 1;
    }

    @Override
    public EvaluationResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> pairValues = CardUtils.getValuesByCount(hand.getCards(), 2);
        if (pairValues.size() != 1) {
            throw new EvaluationException("Pair rule not applicable");
        }
        return new EvaluationResult(Hand.Rank.PAIR, hand, pairValues);
    }
}
