package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public class ThreeOfKindRule extends AbstractPokerRule<Card.Value> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand).filter(element -> getCardsSortedByCount(element, 3).size() == 1).isPresent();
    }

    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        return isValidHand(hand)
                .map(element -> getCardsSortedByCount(element, 3))
                .filter(elementsList -> elementsList.size() == 1)
                .map(elementsList -> new HandResult<>(Hand.Rank.THREE_OF_A_KIND, elementsList.getFirst()))
                .orElse(null);

    }
}
