package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public class StraightRule extends AbstractPokerRule<Card>{
    @Override
    public boolean applicable(Hand hand) {
        return getConsecutiveValuesAndReturnHighestValueCard(hand) != null;
    }

    @Override
    public HandResult<Card> rank(Hand hand) {
        return new HandResult<>(Hand.Rank.STRAIGHT, getConsecutiveValuesAndReturnHighestValueCard(hand));
    }
}
