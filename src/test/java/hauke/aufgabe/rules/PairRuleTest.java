package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.result.ValueResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PairRuleTest {

    @Test
    public void handWithOnePair_shouldReturnPairResult() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));

        PairRule pairRule = new PairRule();
        Assertions.assertThat(pairRule.isApplicable(hand)).isTrue();
        ValueResult result = pairRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.PAIR);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.ACE);
    }

    @Test
    public void handWithAcePair_shpuldReturnAcePair() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));

        PairRule pairRule = new PairRule();
        ValueResult result = pairRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.PAIR);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.ACE);
    }

    @Test
    public void handWithKingPair_shouldReturnKingPair() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));

        PairRule pairRule = new PairRule();
        ValueResult result = pairRule.evaluate(hand);
        Assertions.assertThat(result.rank()).isEqualTo(Hand.Rank.PAIR);
        Assertions.assertThat(result.value()).isEqualTo(Card.Value.KING);
    }

    @Test
    public void handWithoutPair_shouldReturnNoPairResult() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.TEN, Card.Suit.HEARTS));

        PairRule pairRule = new PairRule();
        Assertions.assertThat(pairRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void handWithTwoPairs_shouldNotBeApplicable() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Value.JACK, Card.Suit.HEARTS));

        PairRule pairRule = new PairRule();
        Assertions.assertThat(pairRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void nullHand_shouldNotBeApplicable() {
        Hand hand = null;
        PairRule pairRule = new PairRule();
        Assertions.assertThat(pairRule.isApplicable(hand)).isFalse();
    }

    @Test
    public void emptyHand_shouldNotBeApplicable() {
        Hand hand = new Hand();
        PairRule pairRule = new PairRule();
        Assertions.assertThat(pairRule.isApplicable(hand)).isFalse();
    }
}
