package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public class FourOfKindRule extends AbstractPokerRule<Card.Value> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand)
                .filter(validHand -> getCardsSortedByCount(validHand, 4).size() == 1)
                .isPresent();
    }

    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        return isValidHand(hand)
                .map(validHand -> getCardsSortedByCount(validHand, 4))
                .filter(cards -> cards.size() == 1)
                .map(foundVals -> new HandResult<>(Hand.Rank.FOUR_OF_A_KIND, foundVals.getFirst()))
                .orElse(null);
    }
}
