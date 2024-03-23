package hauke.aufgabe;

import hauke.aufgabe.result.RuleValueResult;
import hauke.aufgabe.result.RuleValuesListResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameEvaluateHandTest {

    @Test
    public void handWithTwoPairs_shouldReturnTwoPairs() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        RuleValuesListResult result = (RuleValuesListResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.TWO_PAIR);
        Assertions.assertThat(result.value().size()).isEqualTo(2);
        Assertions.assertThat(result.value()).containsExactlyInAnyOrder(Card.Value.TWO, Card.Value.THREE);
    }

    @Test
    public void handWithThreeOfKind_shouldReturnThreeOfKind() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        RuleValueResult result = (RuleValueResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.THREE_OF_KIND);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.TWO);
    }

    @Test
    public void handWithFourOfKind_shouldReturnFourOfKind() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        RuleValueResult result = (RuleValueResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FOUR_OF_KIND);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.TWO);
    }

    @Test
    public void handWithFullHouse_shouldReturnFullHouse() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));

        RuleValueResult result = (RuleValueResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FULL_HOUSE);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.TWO);
    }

    @Test
    public void handWithFlush_shouldReturnFlush() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.SEVEN, Card.Suit.CLUBS));

        RuleValuesListResult result = (RuleValuesListResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FLUSH);
        Assertions.assertThat(result.value()).containsExactly(
                Card.Value.QUEEN,
                Card.Value.SEVEN,
                Card.Value.FOUR,
                Card.Value.THREE,
                Card.Value.TWO
        );
    }

    @Test
    public void handWithStraight_shouldReturnStraight() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.SIX, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.SPADES));
        RuleValueResult result = (RuleValueResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.SIX);
    }

    @Test
    public void handWithStraightFlush_shouldReturnStraightFlush() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.SIX, Card.Suit.HEARTS));

        RuleValueResult result = (RuleValueResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT_FLUSH);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.SIX);
    }

    @Test
    public void handWithHighCard_shouldReturnHighCard() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.NINE, Card.Suit.HEARTS));
        RuleValuesListResult result = (RuleValuesListResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.HIGH_CARD);
        Assertions.assertThat(result.value().getFirst()).isEqualTo(Card.Value.NINE);
    }

    @Test
    public void handWithPair_shouldReturnPair() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        RuleValueResult result = (RuleValueResult) game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.PAIR);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.TWO);
    }

}
