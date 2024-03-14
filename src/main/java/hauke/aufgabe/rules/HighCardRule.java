package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.ArrayList;
import java.util.List;

public class HighCardRule implements PokerRule<List<Card>> {
    @Override
    public boolean applicable(Hand hand) {
        return true;
    }

    @Override
    public HandResult<List<Card>> rank(Hand hand) {
        List<Card> resortedCards = hand.getCards().stream().sorted().toList().reversed();
        return new HandResult<>(Hand.Rank.HIGH_CARD, resortedCards);
    }
}
