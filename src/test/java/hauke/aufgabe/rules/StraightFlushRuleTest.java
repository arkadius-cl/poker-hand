package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.result.RuleValueResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StraightFlushRuleTest {

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        StraightFlushRule rule = new StraightFlushRule();
        Assertions.assertThat(rule.isApplicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        StraightFlushRule rule = new StraightFlushRule();
        Assertions.assertThat(rule.isApplicable(null)).isFalse();
    }

    @Test
    public void handWithFlushButNoStraight_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.NINE, Card.Suit.HEARTS));

        StraightFlushRule rule = new StraightFlushRule();
        Assertions.assertThat(rule.isApplicable(hand)).isFalse();
    }

    @Test
    public void handWithStraightButNoFlush_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TEN, Card.Suit.CLUBS));

        StraightFlushRule rule = new StraightFlushRule();
        Assertions.assertThat(rule.isApplicable(hand)).isFalse();
    }

    @Test
    public void handWithStraightFlush_shouldBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TEN, Card.Suit.HEARTS));

        StraightFlushRule rule = new StraightFlushRule();
        Assertions.assertThat(rule.isApplicable(hand)).isTrue();
    }

    @Test
    public void handWithStraightFlush_shouldReturnStraightFlushRank() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TEN, Card.Suit.HEARTS));

        StraightFlushRule rule = new StraightFlushRule();
        RuleValueResult result = rule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT_FLUSH);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.ACE);
    }

    @Test
    public void handWithFlushAndAceLowStraight_shouldBeApplicableAndReturnFive() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        StraightFlushRule rule = new StraightFlushRule();
        Assertions.assertThat(rule.isApplicable(hand)).isTrue();
        RuleValueResult result = rule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT_FLUSH);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.FIVE);
    }

    @Test
    public void handWithFlushAndAceHighStraight_shouldBeApplicableAndReturnAce() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TEN, Card.Suit.HEARTS));

        StraightFlushRule rule = new StraightFlushRule();
        Assertions.assertThat(rule.isApplicable(hand)).isTrue();
        RuleValueResult result = rule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT_FLUSH);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.ACE);
    }
}
