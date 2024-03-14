package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public class ThreeOfKindRule extends AbstractPokerRule<Card.Value> {
    @Override
    public boolean applicable(Hand hand) {
        return getCardsSortedByCount(hand, 3).size() == 1;
    }

    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        return getCardsSortedByCount(hand, 3).stream()
                .map(card -> new HandResult<>(Hand.Rank.THREE_OF_A_KIND, card))
                .toList().getFirst();
    }
}
