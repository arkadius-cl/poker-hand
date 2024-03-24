package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.util.CardUtils;

import java.util.List;
import java.util.function.Predicate;

public class HighCardRule implements EvaluationRule {
    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> true;
    }

    @Override
    public EvaluationResult evaluate(Hand hand) throws EvaluationException {
        List<Card.Value> values = hand.getCards()
                .stream()
                .sorted(CardUtils::compareDescending)
                .map(Card::getValue)
                .toList();
        return new EvaluationResult(Hand.Rank.HIGH_CARD, hand, values);
    }
}
