package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.ValuesListResult;

import java.util.List;
import java.util.function.Predicate;

public class FlushRule extends AbstractPokerRule {

    @Override
    public Predicate<Hand> applicationPredicate() {
        return this::isOneSuit;
    }

    @Override
    public ValuesListResult evaluate(Hand hand) throws EvaluationException {
        if (!isOneSuit(hand)) {
            throw new EvaluationException("Hand is not a flush, because it does not have the same suit for all cards.");
        }
        List<Card.Value> values = hand.getCards().stream().sorted(Card::compareDescending).map(Card::getValue).toList();
        return new ValuesListResult(Hand.Rank.FLUSH, values);
    }
}
