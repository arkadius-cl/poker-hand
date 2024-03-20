package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TwoPairsRuleTest {

    @Test
    public void handWithOnePair_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));

        TwoPairsRule twoPairsRule = new TwoPairsRule();
        Assertions.assertThat(twoPairsRule.applicable(hand)).isFalse();
    }

    @Test
    public void handWithTwoPairs_shouldBeApplicableAndReturnHandResult() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));

        TwoPairsRule twoPairsRule = new TwoPairsRule();
        Assertions.assertThat(twoPairsRule.applicable(hand)).isTrue();
        HandResult<List<Card.Value>> result = twoPairsRule.rank(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.TWO_PAIR);
        Assertions.assertThat(result.payload()).describedAs("Shuld only contain Card.Value.TWO and Card.Value.QUEEN").containsExactlyInAnyOrder(Card.Value.TWO, Card.Value.QUEEN);
    }

    @Test
    public void handWithThreeOfAKind_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));

        TwoPairsRule twoPairsRule = new TwoPairsRule();
        Assertions.assertThat(twoPairsRule.applicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        TwoPairsRule twoPairsRule = new TwoPairsRule();
        Assertions.assertThat(twoPairsRule.applicable(hand)).isFalse();
    }

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        TwoPairsRule twoPairsRule = new TwoPairsRule();
        Assertions.assertThat(twoPairsRule.applicable(hand)).isFalse();
    }

}
