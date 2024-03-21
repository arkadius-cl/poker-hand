package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public class StraightRule extends AbstractPokerRule<Card.Value> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand)
                .filter(element -> getConsecutiveValuesAndReturnHighestValueCard(element) != null)
                .isPresent();
    }

    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        return isValidHand(hand).map(this::getConsecutiveValuesAndReturnHighestValueCard).map(element -> new HandResult<>(Hand.Rank.STRAIGHT, element)).orElse(null);
    }
}
