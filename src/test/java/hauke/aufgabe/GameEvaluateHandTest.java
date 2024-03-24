package hauke.aufgabe;

import hauke.aufgabe.rules.EvaluationResult;
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

        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.TWO_PAIR);
        Assertions.assertThat(result.values().size()).isEqualTo(2);
        Assertions.assertThat(result.values()).containsExactlyInAnyOrder(Card.Value.TWO, Card.Value.THREE);
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

        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.THREE_OF_KIND);
        Assertions.assertThat(result.values()).contains(Card.Value.TWO);
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

        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FOUR_OF_KIND);
        Assertions.assertThat(result.values()).contains(Card.Value.TWO);
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

        EvaluationResult result = (game.evaluateHand(hand));
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FULL_HOUSE);
        Assertions.assertThat(result.values()).contains(Card.Value.TWO);
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

        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.FLUSH);
        Assertions.assertThat(result.values()).containsExactly(
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
        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT);
        Assertions.assertThat(result.values()).contains(Card.Value.SIX);
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

        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.STRAIGHT_FLUSH);
        Assertions.assertThat(result.values()).contains(Card.Value.SIX);
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
        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.HIGH_CARD);
        Assertions.assertThat(result.values().getFirst()).isEqualTo(Card.Value.NINE);
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
        EvaluationResult result = game.evaluateHand(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.PAIR);
        Assertions.assertThat(result.values()).contains(Card.Value.TWO);
    }

}
