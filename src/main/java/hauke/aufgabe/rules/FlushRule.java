package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;

public class FlushRule extends AbstractPokerRule<List<Card>> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand).filter(this::isOneSuit).isPresent();
    }

    @Override
    public HandResult<List<Card>> rank(Hand hand) {
        return isValidHand(hand).map(h -> h.getCards().stream().sorted().toList().reversed()).map(result -> new HandResult<>(Hand.Rank.FLUSH, result)).orElse(null);
    }
}
