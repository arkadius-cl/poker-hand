package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.ValueResult;

import java.util.List;
import java.util.function.Predicate;

public class ThreeOfKindRule extends AbstractPokerRule {

    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> getCardValuesSortedByCount(hand, 3).size() == 1;
    }

    @Override
    public ValueResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> values = getCardValuesSortedByCount(hand, 3);
        if (values.size() != 1) {
            throw new EvaluationException("Three of a kind rule not applicable");
        }
        return new ValueResult(Hand.Rank.THREE_OF_A_KIND, values.getFirst());
    }
}
