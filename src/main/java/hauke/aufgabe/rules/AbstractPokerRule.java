package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractPokerRule<T> implements PokerRule<T>{

    protected Map<Card.Value, List<Card>> groupByValue(Hand hand) {
        return hand.getCards().stream().collect(
                Collectors.groupingBy(Card::getValue, Collectors.toList())
        );
    }

    protected List<Card> getCardsSortedByCount(Hand hand, int count) {
        return groupByValue(hand).values().stream()
                .filter(cards -> cards.size() == count)
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
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


}
