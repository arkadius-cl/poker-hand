package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreeOfKindRuleTest {

    @Test
    public void handWithTwoPairs_shouldNotBeApplicable(){
        Hand hand  = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));

        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        Assertions.assertThat(threeOfKindRule.applicable(hand)).isFalse();
    }

    @Test
    public void handWithThreeOfKind_shouldBeApplicable(){
        Hand hand  = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));

        ThreeOfKindRule threeOfKindRule = new ThreeOfKindRule();
        Assertions.assertThat(threeOfKindRule.applicable(hand)).isTrue();
        HandResult<Card.Value> result = threeOfKindRule.rank(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.THREE_OF_A_KIND);
        Assertions.assertThat(result.payload()).isEqualTo(Card.Value.TWO);
    }
}