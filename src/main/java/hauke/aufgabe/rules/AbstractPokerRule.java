package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractPokerRule implements EvaluationRule {


    /**
     * Groups the cards of the hand by their value.
     *
     * @param hand the hand to group
     * @return the grouped cards as a Map where the key is the card value and the value is a list of cards with that value
     */
    protected Map<Card.Value, List<Card>> groupByValue(Hand hand) {
        return hand.getCards().stream().collect(
                Collectors.groupingBy(Card::getValue, Collectors.toList())
        );
    }

    /**
     * Returns a list of card values that appear a certain number of times in the hand.
     *
     * @param hand  the hand to check
     * @param count the number of times a card value should appear in the hand
     * @return a list of card values that appear 'count' times in the hand
     */
    protected List<Card.Value> getCardValuesSortedByCount(Hand hand, int count) {
        return groupByValue(hand).entrySet().stream()
                .filter(cardEntry -> cardEntry.getValue().size() == count)
                .map(Map.Entry::getKey)
                .distinct()
                .toList();
    }

    /**
     * Checks if the hand contains a sequence of consecutive card values and returns the highest card value in the sequence.
     * If the hand contains an Ace, it can be considered as the lowest card value (Ace, 2, 3, 4, 5) or the highest (10, Jack, Queen, King, Ace).
     *
     * @param hand the hand to check
     * @return the highest card value in the sequence, or null if there is no sequence
     */
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


    /**
     * Checks if all cards in the hand have the same suit.
     *
     * @param hand the hand to check
     * @return true if all cards have the same suit, false otherwise
     */
    protected boolean isOneSuit(Hand hand) {
        return hand.getCards()
                .stream()
                .map(Card::getSuit)
                .distinct()
                .count() == 1;
    }

}
