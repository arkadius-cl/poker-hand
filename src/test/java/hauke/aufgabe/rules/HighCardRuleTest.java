package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

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
        Assertions.assertThat(highCardRule.isApplicable(hand)).isTrue();
        EvaluationResult result = highCardRule.evaluate(hand);
        Assertions.assertThat(result.values()).isSortedAccordingTo(Comparator.comparing(Card.Value::ordinal).reversed());
    }

    @Test
    public void handWithAceAsHighestCard_shouldReturnHighCardResult() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.EIGHT, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));

        HighCardRule highCardRule = new HighCardRule();
        Assertions.assertThat(highCardRule.isApplicable(hand)).isTrue();
        EvaluationResult result = highCardRule.evaluate(hand);
        Assertions.assertThat(result.values()).isSortedAccordingTo(Comparator.comparing(Card.Value::ordinal).reversed());
    }

    @Test
    public void handWithNoCards_shouldNotBeApplicable() {
        Hand hand = new Hand();
        HighCardRule highCardRule = new HighCardRule();
        Assertions.assertThat(highCardRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        HighCardRule highCardRule = new HighCardRule();
        Assertions.assertThat(highCardRule.isApplicable(hand)).isFalse();
    }
}
