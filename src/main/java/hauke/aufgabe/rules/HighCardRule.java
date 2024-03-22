package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.ValuesListResult;

import java.util.List;
import java.util.function.Predicate;

public class HighCardRule extends AbstractPokerRule {
    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> true;
    }

    @Override
    public ValuesListResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> values = hand.getCards()
                .stream()
                .sorted(Card::compareDescending)
                .map(Card::getValue)
                .toList();
        return new ValuesListResult(Hand.Rank.HIGH_CARD, values);
    }
}
