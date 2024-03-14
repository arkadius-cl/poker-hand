package hauke.aufgabe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CardTest {

    @Test
    public void listWithRandomCards_shouldBeSorted() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Value.ACE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        cards.add(new Card(Card.Value.FIVE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Value.THREE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        cards.sort(Card::compareTo);
        Assertions.assertThat(cards).isSortedAccordingTo(Card::compareTo);
    }

    @Test
    public void addRandomCardToList_shouldBeSorted() {
        List<Card> cards = new ArrayList<>();
        IntStream.range(0,10).forEach(i -> cards.add(new Card()));
        cards.sort(Card::compareTo);
        Assertions.assertThat(cards).isSortedAccordingTo(Card::compareTo);
    }

}
