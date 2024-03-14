package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;

public class FlushRule extends AbstractPokerRule<List<Card>> {
    @Override
    public boolean applicable(Hand hand) {
        return isOneSuit(hand);
    }

    @Override
    public HandResult<List<Card>> rank(Hand hand) {
        return new HandResult<>(Hand.Rank.FLUSH, hand.getCards().stream().sorted().toList().reversed());
    }
}
