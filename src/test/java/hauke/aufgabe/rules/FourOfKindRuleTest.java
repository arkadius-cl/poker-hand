package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FourOfKindRuleTest {

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.isApplicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.isApplicable(null)).isFalse();
    }

    @Test
    public void handWithFourOfKind_shouldBeApplicableAndReturnTwo() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.SPADES));
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.isApplicable(hand)).isTrue();
        EvaluationResult result = rule.evaluate(hand);
        Assertions.assertThat(result.values()).hasSize(1);
        Assertions.assertThat(result.values().getFirst()).isEqualTo(Card.Value.TWO);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FOUR_OF_KIND);
    }

    @Test
    public void handWithFourAces_shouldBeApplicableAndReturnAce() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.isApplicable(hand)).isTrue();
        EvaluationResult result = rule.evaluate(hand);
        Assertions.assertThat(result.values()).hasSize(1);
        Assertions.assertThat(result.values().getFirst()).isEqualTo(Card.Value.ACE);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FOUR_OF_KIND);
    }

    @Test
    public void handWithThreeOfKind_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.isApplicable(hand)).isFalse();
    }

}
