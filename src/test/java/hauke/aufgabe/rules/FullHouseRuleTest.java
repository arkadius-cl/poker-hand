package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FullHouseRuleTest {

    @Test
    public void handWithNoFullHouse_shudNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.SEVEN, Card.Suit.CLUBS));

        FullHouseRule fullHouseRule = new FullHouseRule();
        boolean applicable = fullHouseRule.applicable(hand);

        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    public void handWithFullHouse_shouldBeApplicableAndReturnFullHouseRank() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.DIAMONDS));

        FullHouseRule fullHouseRule = new FullHouseRule();
        boolean applicable = fullHouseRule.applicable(hand);

        Assertions.assertThat(applicable).isTrue();
        HandResult<Card.Value> result = fullHouseRule.rank(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.FULL_HOUSE);
        Assertions.assertThat(result.payload()).isEqualTo(Card.Value.FOUR);
    }

    @Test
    public void handWithFourOfKind_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));

        FullHouseRule fullHouseRule = new FullHouseRule();
        boolean applicable = fullHouseRule.applicable(hand);

        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    public void handWithThreeOfKind_shouldNotbeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        FullHouseRule fullHouseRule = new FullHouseRule();
        boolean applicable = fullHouseRule.applicable(hand);

        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        FullHouseRule fullHouseRule = new FullHouseRule();
        boolean applicable = fullHouseRule.applicable(hand);
        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        FullHouseRule fullHouseRule = new FullHouseRule();
        boolean applicable = fullHouseRule.applicable(hand);
        Assertions.assertThat(applicable).isFalse();
    }
}
