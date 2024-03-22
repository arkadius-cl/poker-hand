package hauke.aufgabe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameEvaluateHandTest {

    @Test
    public void testEvaluateHand() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.STRAIGHT_FLUSH);
    }

    @Test
    public void handWithTwoPairs_shouldReturnTwoPairs() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.TWO_PAIR);
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

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.THREE_OF_A_KIND);
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

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.FOUR_OF_A_KIND);
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

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.FULL_HOUSE);
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

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.FLUSH);
    }

    @Test
    public void handWithStraight_shouldReturnStraight() {
        Game game = new Game();
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.THREE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.FOUR, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Value.FIVE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Value.SIX, Card.Suit.HEARTS));

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.STRAIGHT);
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

        HandResult<?> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.STRAIGHT_FLUSH);
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
        HandResult<List<Card>> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.HIGH_CARD);
        Assertions.assertThat(result.payload().getFirst().getValue()).isEqualTo(Card.Value.NINE);
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
        HandResult<Card.Value> result = game.evaluateHand(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.PAIR);
        Assertions.assertThat(result.payload()).isEqualTo(Card.Value.TWO);
    }

}
