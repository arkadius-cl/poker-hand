package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;

public class PairRule extends AbstractPokerRule<Card.Value>{
    @Override
    public boolean applicable(Hand hand) {
        return getCardsSortedByCount(hand, 2).size() == 1;
    }

    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        List<Card.Value> foundPair = getCardsSortedByCount(hand, 2);
        return new HandResult<>(Hand.Rank.PAIR, foundPair.getFirst());
    }
}
