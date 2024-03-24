package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.util.CardUtils;

import java.util.List;
import java.util.function.Predicate;

public class FlushRule implements EvaluationRule {

    @Override
    public Predicate<Hand> applicationPredicate() {
        return hand -> CardUtils.isOneSuit(hand.getCards());
    }

    @Override
    public EvaluationResult evaluate(Hand hand) throws EvaluationException {
        if (!CardUtils.isOneSuit(hand.getCards())) {
            throw new EvaluationException("Hand is not a flush, because it does not have the same suit for all cards.");
        }
        List<Card.Value> values = hand.getCards()
                .stream()
                .sorted(CardUtils::compareDescending)
                .map(Card::getValue)
                .toList();
        return new EvaluationResult(Hand.Rank.FLUSH, hand, values);
    }
}
