package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.util.CardUtils;

import java.util.List;
import java.util.function.Predicate;

public class ThreeOfKindRule implements EvaluationRule{

    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> CardUtils.getValuesByCount(hand.getCards(), 3).size() == 1;
    }

    @Override
    public EvaluationResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> values = CardUtils.getValuesByCount(hand.getCards(), 3);
        if (values.size() != 1) {
            throw new EvaluationException("Three of a kind rule not applicable");
        }
        return new EvaluationResult(Hand.Rank.THREE_OF_KIND, hand, values);
    }
}
