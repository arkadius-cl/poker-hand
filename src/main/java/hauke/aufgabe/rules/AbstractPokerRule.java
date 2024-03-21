package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractPokerRule<T> implements PokerRule<T>{

    protected Optional<Hand> isValidHand(Hand hand) {
        return Optional.ofNullable(hand)
                .filter(h -> h.getCards().size() == 5);
    }

    protected Map<Card.Value, List<Card>> groupByValue(Hand hand) {
        return hand.getCards().stream().collect(
                Collectors.groupingBy(Card::getValue, Collectors.toList())
        );
    }

    protected List<Card.Value> getCardsSortedByCount(Hand hand, int count) {
        return groupByValue(hand).entrySet().stream()
                .filter(cardEntry -> cardEntry.getValue().size() == count)
                .map(Map.Entry::getKey)
                .distinct()
                .toList();
    }

    protected Card getConsecutiveValuesAndReturnHighestValueCard(Hand hand) {
        List<Card> cards = hand.getCards().stream().sorted().toList();
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue().ordinal() + 1 != cards.get(i + 1).getValue().ordinal()) {
                return null;
            }
        }
        return cards.getLast();
    }

    protected boolean isOneSuit(Hand hand) {
        return hand.getCards().stream().map(Card::getSuit).distinct().count() == 1;
    }

}
