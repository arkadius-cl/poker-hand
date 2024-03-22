package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.ValueResult;

import java.util.List;
import java.util.function.Predicate;

public class FourOfKindRule extends AbstractPokerRule {


    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> getCardValuesSortedByCount(hand, 4).size() == 1;
    }

    @Override
    public ValueResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> cards = getCardValuesSortedByCount(hand, 4);
        if (cards.size() != 1) {
            throw new EvaluationException("Four of a kind rule not applicable");
        }
        return new ValueResult(Hand.Rank.FOUR_OF_A_KIND, cards.getFirst());
    }
}
