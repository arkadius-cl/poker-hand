package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;

public class TwoPairsRule extends AbstractPokerRule<List<Card.Value>> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand)
                .filter(element -> getCardsSortedByCount(element, 2).size() == 2)
                .isPresent();
    }

    @Override
    public HandResult<List<Card.Value>> rank(Hand hand) {
        return isValidHand(hand)
                .map(element -> getCardsSortedByCount(element, 2))
                .filter(foundElements -> foundElements.size() == 2)
                .map(foundElementsList -> new HandResult<>(Hand.Rank.TWO_PAIR, foundElementsList)).orElse(null);
    }
}
