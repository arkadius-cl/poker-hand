package hauke.aufgabe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Card {
    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    /**
     * Creates a new card with a random suit and value.
     */
    public Card() {
        // random suit
        this.suit = randomElement(Suit.values());//Suit.values()[(int) (Math.random() * Suit.values().length)];
        // random value
        this.value = randomElement(Value.values());//Value.values()[(int) (Math.random() * Value.values().length)];
    }

    @EqualsAndHashCode.Include
    private final Value value;
    @EqualsAndHashCode.Include
    private final Suit suit;

    /**
     * Returns a random element from the given array of elements.
     *
     * @param elements the array of elements to choose from
     * @param <T>      the type of elements in the array
     * @return a random element from the array
     */
    private <T> T randomElement(T[] elements) {
        int randomIndex = (int) (Math.random() * elements.length);
        return elements[randomIndex];
    }

    /**
     * Compares this card to another card in ascending order.
     *
     * @param o the other card to compare to
     * @return a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the
     * other card
     */
    public static int compareAscending(Card first, Card second) {
        return Integer.compare(first.value.ordinal(), second.value.ordinal());
    }

    /**
     * Compares this card to another card in descending order.
     *
     * @param o the other card to compare to
     * @return a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the
     * other card
     */
    public static int compareDescending(Card first, Card second) {
        return Integer.compare(second.value.ordinal(), first.value.ordinal());
    }

}
