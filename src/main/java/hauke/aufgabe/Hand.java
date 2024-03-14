package hauke.aufgabe;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) throws IllegalArgumentException {
        if (Objects.isNull(card)) {
            throw new IllegalArgumentException("Card must not be null");
        }
        if (cards.size() >= 5) {
            throw new IllegalArgumentException("Hand is full");
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException("Card is already in hand");
        }
        cards.add(card);
    }

}
