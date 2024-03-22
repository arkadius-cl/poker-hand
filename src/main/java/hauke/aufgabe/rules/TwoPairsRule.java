package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.ValuesListResult;

import java.util.List;
import java.util.function.Predicate;

public class TwoPairsRule extends AbstractPokerRule {

    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> getCardValuesSortedByCount(hand, 2).size() == 2;
    }

    @Override
    public ValuesListResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> values = getCardValuesSortedByCount(hand, 2);
        if (values.size() != 2) {
            throw new EvaluationException("Two pairs rule not applicable");
        }
        return new ValuesListResult(Hand.Rank.TWO_PAIR, values);
    }
}
