package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.ValueResult;

import java.util.List;
import java.util.function.Predicate;

public class PairRule extends AbstractPokerRule {
    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> getCardValuesSortedByCount(hand, 2).size() == 1;
    }

    @Override
    public ValueResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> pairValues = getCardValuesSortedByCount(hand, 2);
        if (pairValues.size() != 1) {
            throw new EvaluationException("Pair rule not applicable");
        }
        return new ValueResult(Hand.Rank.PAIR, pairValues.getFirst());
    }
}
