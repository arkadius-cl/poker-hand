package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;
import java.util.Map;

public class FullHouseRule extends AbstractPokerRule<Card.Value> {
    @Override
    public boolean applicable(Hand hand) {
        Map<Card.Value, List<Card>> groupedByValue = groupByValue(hand);
        return groupedByValue.size() == 2 && groupedByValue.values().stream().anyMatch(cards -> cards.size() == 3) && groupedByValue.values().stream().anyMatch(cards -> cards.size() == 2);
    }


    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        Map<Card.Value, List<Card>> groupedByValue = groupByValue(hand);
        for (Map.Entry<Card.Value, List<Card>> entry : groupedByValue.entrySet()) {
            if (entry.getValue().size() == 3) {
                return new HandResult<>(Hand.Rank.FULL_HOUSE, entry.getKey());
            }
        }
        return null;
    }
}
