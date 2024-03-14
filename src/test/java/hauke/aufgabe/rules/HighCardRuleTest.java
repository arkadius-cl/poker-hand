package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class HighCardRuleTest {

    @Test
    public void handWithTenAsHighestCard_shouldReturnHighCardResult() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.EIGHT, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));

        HighCardRule highCardRule = new HighCardRule();
        Assertions.assertThat(highCardRule.applicable(hand)).isTrue();
        HandResult<List<Card>> result = highCardRule.rank(hand);
        Assertions.assertThat(result.payload()).isSortedAccordingTo(Comparator.comparing(Card::getValue).reversed());
    }
}