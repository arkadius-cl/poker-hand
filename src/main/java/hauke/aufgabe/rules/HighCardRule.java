package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;
import java.util.stream.Stream;

public class HighCardRule extends AbstractPokerRule<List<Card>> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand).isPresent();
    }

    @Override
    public HandResult<List<Card>> rank(Hand hand) {
        List<Card> resortedCards = hand.getCards().stream().sorted().toList().reversed();
        return isValidHand(hand)
                .map(Hand::getCards)
                .map(Stream::of)
                .map(Stream::sorted)
                .map(Stream::toList)
                .map(List::reversed)
                .map(cards -> {
                    return new HandResult<>(Hand.Rank.HIGH_CARD, resortedCards);
                }).orElse(null);
    }
}
