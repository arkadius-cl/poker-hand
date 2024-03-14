package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;
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
        Assertions.assertThat(pairRule.applicable(hand)).isTrue();
        HandResult<Card.Value> result = pairRule.rank(hand);
        Assertions.assertThat(result.handRank()).isEqualTo(Hand.Rank.PAIR);
        Assertions.assertThat(result.payload()).isEqualTo(Card.Value.ACE);
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
        Assertions.assertThat(pairRule.applicable(hand)).isFalse();
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
        Assertions.assertThat(pairRule.applicable(hand)).isFalse();
    }
}
