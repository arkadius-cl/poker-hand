package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.List;
import java.util.Map;

public class FullHouseRule extends AbstractPokerRule<Card.Value> {
    @Override
    public boolean applicable(Hand hand) {
        return isValidHand(hand)
                .map(this::groupByValue)
                .filter(groupedByValue -> groupedByValue.size() == 2)
                .filter(groupedByValue ->
                        groupedByValue.values().stream().anyMatch(cards -> cards.size() == 3) &&
                                groupedByValue.values().stream().anyMatch(cards -> cards.size() == 2))
                .isPresent();
    }


    @Override
    public HandResult<Card.Value> rank(Hand hand) {
        return isValidHand(hand).map(this::groupByValue)
                .map(groupedByValue -> {
                    for (Map.Entry<Card.Value, List<Card>> entry : groupedByValue.entrySet()) {
                        if (entry.getValue().size() == 3) {
                            return new HandResult<>(Hand.Rank.FULL_HOUSE, entry.getKey());
                        }
                    }
                    return null;
                })
                .orElse(null);
    }
}
