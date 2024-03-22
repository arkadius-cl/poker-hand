package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;
import java.util.stream.Stream;

public class FlushRule extends AbstractPokerRule<List<Card>> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand).filter(this::isOneSuit).isPresent();
    }

    @Override
    public HandResult<List<Card>> rank(Hand hand) {
        return isValidHand(hand)
                .filter(this::isOneSuit)
                .map(Hand::getCards)
                .map(List::stream)
                .map(Stream::sorted)
                .map(Stream::toList)
                .map(List::reversed)
                .map(cardList -> new HandResult<>(Hand.Rank.FLUSH, cardList))
                .orElse(null);
    }
}
