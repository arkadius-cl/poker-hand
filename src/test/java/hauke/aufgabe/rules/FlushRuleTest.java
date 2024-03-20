package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class FlushRuleTest {

    @Test
    public void handWithNoFlush_shudNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.SEVEN, Card.Suit.CLUBS));

        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.applicable(hand)).isFalse();
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
        Assertions.assertThat(flushRule.applicable(hand)).isTrue();
        HandResult<List<Card>> result = flushRule.rank(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.FLUSH);
        Assertions.assertThat(result.payload()).isSortedAccordingTo(Comparator.comparing(Card::getValue).reversed());
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
        Assertions.assertThat(flushRule.applicable(hand)).isTrue();
        HandResult<List<Card>> result = flushRule.rank(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.FLUSH);
        Assertions.assertThat(result.payload()).isSortedAccordingTo(Comparator.comparing(Card::getValue).reversed());
    }

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.applicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        FlushRule flushRule = new FlushRule();
        Assertions.assertThat(flushRule.applicable(hand)).isFalse();
    }

}
