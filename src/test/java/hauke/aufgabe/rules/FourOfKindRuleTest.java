package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FourOfKindRuleTest {

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.applicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.applicable(null)).isFalse();
    }

    @Test
    public void handWithFourOfKind_shouldBeApplicableAndReturnTwo() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.CLUBS));
        FourOfKindRule rule = new FourOfKindRule();
        Assertions.assertThat(rule.applicable(hand)).isTrue();
        HandResult<Card.Value> result = rule.rank(hand);
        Assertions.assertThat(result.payload()).isEqualTo(Card.Value.TWO);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.FOUR_OF_A_KIND);

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
        Assertions.assertThat(rule.applicable(hand)).isFalse();
    }

}
