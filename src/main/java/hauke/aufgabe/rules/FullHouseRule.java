package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;
import java.util.Map;

public class FullHouseRule extends AbstractPokerRule<Card>{
    @Override
    public boolean applicable(Hand hand) {
        Map<Card.Value, List<Card>> groupedByValue = groupByValue(hand);
        return groupedByValue.size() == 2 && groupedByValue.values().stream().anyMatch(cards -> cards.size() == 3) && groupedByValue.values().stream().anyMatch(cards -> cards.size() == 2);
    }

    private void getLast() {
    }

    @Override
    public HandResult<Card> rank(Hand hand) {
        return null;
    }
}
