package hauke.aufgabe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    public void addNULLCard_shouldThrowIllegalArgumentException() {
        Hand hand = new Hand();
        Assertions.assertThrows(IllegalArgumentException.class, () -> hand.addCard(null));
    }

    @Test
    public void addSameCardTwice_shouldThrowIllegalArgumentException() {
        Hand hand = new Hand();
        Card card = new Card();
        hand.addCard(card);
        Assertions.assertThrows(IllegalArgumentException.class, () -> hand.addCard(card));
    }

    @Test
    public void addSixCards_shouldThrowIllegalArgumentException() {
        Hand hand = new Hand();
        for (int i = 0; i < 5; i++) {
            hand.addCard(new Card());
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> hand.addCard(new Card()));
    }
}
