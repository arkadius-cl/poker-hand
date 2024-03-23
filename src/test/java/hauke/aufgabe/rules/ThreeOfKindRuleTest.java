package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.result.RuleValueResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreeOfKindRuleTest {

    @Test
    public void handWithTwoPairs_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));

        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        Assertions.assertThat(threeOfKindRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void handWithThreeOfKind_shouldBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));

        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        RuleValueResult result = threeOfKindRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.THREE_OF_KIND);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.TWO);
    }

    @Test
    public void handWithThreeOfKind_shouldReturnThreeOfKind() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));

        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        RuleValueResult result = threeOfKindRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.THREE_OF_KIND);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.TWO);
    }

    @Test
    public void handWithFourOfKind_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));

        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        Assertions.assertThat(threeOfKindRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        Assertions.assertThat(threeOfKindRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        Assertions.assertThat(threeOfKindRule.isApplicable(hand)).isFalse();
    }
}
