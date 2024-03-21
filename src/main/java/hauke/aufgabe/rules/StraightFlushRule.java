package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public class StraightFlushRule extends AbstractPokerRule<Card.Value>{
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand)
                .filter(this::isOneSuit)
                .filter(element -> getConsecutiveValuesAndReturnHighestValueCard(element) != null)
                .isPresent();

    }

    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        return isValidHand(hand)
                .filter(this::isOneSuit)
                .map(this::getConsecutiveValuesAndReturnHighestValueCard)
                .map(value -> new HandResult<>(Hand.Rank.STRAIGHT_FLUSH, value))
                .orElse(null);
    }
}
