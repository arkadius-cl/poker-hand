package hauke.aufgabe.util;


import hauke.aufgabe.Card;

import java.util.*;
import java.util.stream.Collectors;

public class CardUtils {

    private CardUtils() {
    }

    /**
     * Checks if all cards in a hand are of the same suit.
     *
     * @param handCards the list of cards in the hand
     * @return true if all cards are of the same suit, false otherwise
     */
    public static boolean isOneSuit(List<Card> handCards) {
        if (Objects.isNull(handCards) || handCards.isEmpty()) {
            return false;
        }
        return handCards
                .stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size() == 1;
    }

    /**
     * Groups cards in a hand by their value.
     *
     * @param handCards the list of cards in the hand
     * @return a map where the keys are card values and the values are lists of cards with that value
     */
    public static Map<Card.Value, List<Card>> groupByValue(List<Card> handCards) {
        return handCards
                .stream()
                .collect(
                        Collectors.groupingBy(Card::getValue, Collectors.toList())
                );
    }

    /**
     * Finds the card with the highest value in a hand.
     *
     * @param handCards the list of cards in the hand
     * @return the value of the card with the highest value, or null if the hand is empty
     */
    public static Card.Value getHighestValue(List<Card> handCards) {
        return handCards
                .stream()
                .map(Card::getValue)
                .max(Comparator.comparingInt(Enum::ordinal))
                .orElse(null);
    }

    /**
     * Finds all card values in a hand that appear a certain number of times.
     *
     * @param handCards the list of cards in the hand
     * @param count the number of times a card value should appear
     * @return a list of card values that appear the specified number of times
     */
    public static List<Card.Value> getValuesByCount(List<Card> handCards, int count) {
        return groupByValue(handCards)
                .entrySet()
                .stream()
                .filter(cardEntry -> cardEntry.getValue().size() == count)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Finds the highest card in a straight in a hand.
     *
     * @param handCards the list of cards in the hand
     * @return the value of the highest card in the straight, or null if there is no straight
     */
    public static Card.Value getStraightHighestValue(List<Card> handCards) {
        List<Card.Value> sortedCards = handCards
                .stream()
                .map(Card::getValue)
                .sorted().toList();
        boolean aceLowStraight = Collections.indexOfSubList(sortedCards, List.of(Card.Value.TWO, Card.Value.THREE, Card.Value.FOUR, Card.Value.FIVE, Card.Value.ACE)) != -1;
        int cardsSize = aceLowStraight ? 3 : 4;
        for (int i = 0; i < cardsSize; i++) {
            if (sortedCards.get(i).ordinal() + 1 != sortedCards.get(i + 1).ordinal()) {
                return null;
            }
        }
        return aceLowStraight ? sortedCards.get(3) : sortedCards.getLast();
    }


}
