package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractPokerRule<T> implements PokerRule<T> {

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

    protected Card.Value getConsecutiveValuesAndReturnHighestValueCard(Hand hand) {
        List<Card.Value> cards = hand.getCards().stream().map(Card::getValue).sorted().toList();
        boolean aceLowStraight = Collections.indexOfSubList(cards, List.of(Card.Value.TWO, Card.Value.THREE, Card.Value.FOUR, Card.Value.FIVE, Card.Value.ACE)) != -1;
        int cardsSize = aceLowStraight ? 3 : 4;
        for (int i = 0; i < cardsSize; i++) {
            if (cards.get(i).ordinal() + 1 != cards.get(i + 1).ordinal()) {
                return null;
            }
        }
        return aceLowStraight ? cards.get(3) : cards.getLast();
    }

    protected boolean isOneSuit(Hand hand) {
        return hand.getCards().stream().map(Card::getSuit).distinct().count() == 1;
    }

}
