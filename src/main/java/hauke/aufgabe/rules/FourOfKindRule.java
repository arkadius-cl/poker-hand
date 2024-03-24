package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.util.CardUtils;

import java.util.List;
import java.util.function.Predicate;

public class FourOfKindRule implements EvaluationRule {


    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> CardUtils.getValuesByCount(hand.getCards(), 4).size() == 1;
    }

    @Override
    public EvaluationResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> cards = CardUtils.getValuesByCount(hand.getCards(), 4);
        if (cards.size() != 1) {
            throw new EvaluationException("Four of a kind rule not applicable");
        }
        return new EvaluationResult(Hand.Rank.FOUR_OF_KIND, hand, cards);
    }
}
