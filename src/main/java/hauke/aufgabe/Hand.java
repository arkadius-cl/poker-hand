package hauke.aufgabe;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class Hand {

    public enum Rank {
        HIGH_CARD,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH;
    }
    private final List<Card> cards = new ArrayList<>();

    /**
     * Returns the cards in the hand.
     *
     * @return the cards in the hand (immutable copy)
     */
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    /**
     * Adds a card to the hand.
     *
     * @param card the card to add
     * @throws IllegalArgumentException if the card is null, the hand is full or the card is already in the hand
     */
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
