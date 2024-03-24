package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class FlushRuleTest {


    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void handWithNoFlush_shudNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.SEVEN, Card.Suit.CLUBS));

        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void handWithFlush_shouldBeApplicableAndReturnReversedListOfCards() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.SEVEN, Card.Suit.CLUBS));

        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.isApplicable(hand)).isTrue();
        EvaluationResult result = flushRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FLUSH);
        Assertions.assertThat(result.values()).isSortedAccordingTo(Comparator.comparing(Card.Value::ordinal).reversed());
    }

    @Test
    public void handWithAceLowFlush_shouldBeApplicableAndReturnReversedListOfCards() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.CLUBS));

        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.isApplicable(hand)).isTrue();
        EvaluationResult result = flushRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FLUSH);
        Assertions.assertThat(result.values()).isSortedAccordingTo(Comparator.comparing(Card.Value::ordinal).reversed());
    }

    @Test
    public void handWithTwoPairs_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.isApplicable(hand)).isFalse();
    }

}
