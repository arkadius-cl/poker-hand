package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.result.RuleValueResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StraightRuleTest {

    @Test
    public void handWithNoStraight_shudNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.SEVEN, Card.Suit.CLUBS));
        StraightRule straightRule = new StraightRule();

        boolean applicable = straightRule.isApplicable(hand);
        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    public void handWithStraight_shouldBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.SIX, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));

        StraightRule straightRule = new StraightRule();
        Assertions.assertThat(straightRule.isApplicable(hand)).isTrue();
        RuleValueResult result = straightRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.SIX);
    }

    @Test
    public void handWithAceLowStraight_shouldBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.CLUBS));

        StraightRule straightRule = new StraightRule();
        Assertions.assertThat(straightRule.isApplicable(hand)).isTrue();
        RuleValueResult result = straightRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.FIVE);
    }

    @Test
    public void handWithAceHighStraight_shouldBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TEN, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.CLUBS));

        StraightRule straightRule = new StraightRule();
        Assertions.assertThat(straightRule.isApplicable(hand)).isTrue();
        RuleValueResult result = straightRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.ACE);
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        StraightRule straightRule = new StraightRule();
        boolean applicable = straightRule.isApplicable(hand);
        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        StraightRule straightRule = new StraightRule();
        boolean applicable = straightRule.isApplicable(hand);
        Assertions.assertThat(applicable).isFalse();
    }
}
