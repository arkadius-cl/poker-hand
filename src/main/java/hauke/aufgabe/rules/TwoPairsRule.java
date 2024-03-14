package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;

public class TwoPairsRule extends AbstractPokerRule<List<Card.Value>> {
    @Override
    public boolean applicable(Hand hand) {
        return getCardsSortedByCount(hand, 2).size() == 2;
    }

    @Override
    public HandResult<List<Card.Value>> rank(Hand hand) {
        List<Card.Value> foundPairs = getCardsSortedByCount(hand, 2);
        return new HandResult<>(Hand.Rank.TWO_PAIR, foundPairs);
    }
}
