package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public class PairRule extends AbstractPokerRule<Card.Value> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand).filter(element -> this.getCardsSortedByCount(element, 2).size() == 1).isPresent();
    }

    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        return isValidHand(hand).map(element -> new HandResult<>(Hand.Rank.PAIR, getCardsSortedByCount(element, 2).getFirst())).orElse(null);
    }
}
