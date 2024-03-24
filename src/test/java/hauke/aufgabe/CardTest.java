package hauke.aufgabe;

import hauke.aufgabe.util.CardUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    @Test
    public void testCompareAscending() {
        Card card1 = new Card(Card.Value.TWO, Card.Suit.HEARTS);
        Card card2 = new Card(Card.Value.THREE, Card.Suit.HEARTS);

        assertTrue(CardUtils.compareAscending(card1, card2) < 0);
    }

    @Test
    public void testCompareDescending() {
        Card card1 = new Card(Card.Value.TWO, Card.Suit.HEARTS);
        Card card2 = new Card(Card.Value.THREE, Card.Suit.HEARTS);

        assertTrue(CardUtils.compareDescending(card1, card2) > 0);
    }

    @Test
    public void oneHundredRandomCards_allShouldBeCorrect() {
        List<Card> cards = IntStream.range(0, 100).mapToObj(i -> new Card()).toList();
        Assertions.assertThat(cards).allMatch(card -> card.getValue() != null && card.getSuit() != null);
    }

}
