package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.rules.EvaluationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PairEvaluatorTest {

    @Test
    public void twoEvaluationResultsWithPairOfTwo_secondIndexShouldReturnWinner_shouldReturnWinner() {
        Hand adamHand = new Hand("Adam");
        adamHand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        adamHand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        adamHand.addCard(new Card(Card.Value.NINE, Card.Suit.HEARTS));
        adamHand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        adamHand.addCard(new Card(Card.Value.SIX, Card.Suit.HEARTS));
        EvaluationResult adam = new EvaluationResult(Hand.Rank.PAIR, adamHand, List.of(Card.Value.TWO));
        Hand bobHand = new Hand("Bob");
        bobHand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        bobHand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        bobHand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        bobHand.addCard(new Card(Card.Value.FIVE, Card.Suit.CLUBS));
        bobHand.addCard(new Card(Card.Value.SEVEN, Card.Suit.HEARTS));
        EvaluationResult bob = new EvaluationResult(Hand.Rank.PAIR,bobHand, List.of(Card.Value.TWO));

        PairEvaluator pairEvaluator = new PairEvaluator();
        String winner = pairEvaluator.evaluate(List.of(adam, bob));
        Assertions.assertThat(winner).isEqualTo("Adam");
    }

    @Test
    public void  fourEvaluatesWithRandomPairs_twoPairsAreIdentical_shouldREturnByHighestCard(){
        Hand adamHand = new Hand("Adam");
        adamHand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        adamHand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        adamHand.addCard(new Card(Card.Value.NINE, Card.Suit.HEARTS));
        adamHand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        adamHand.addCard(new Card(Card.Value.SIX, Card.Suit.HEARTS));
        EvaluationResult adam = new EvaluationResult(Hand.Rank.PAIR, adamHand, List.of(Card.Value.TWO));
        Hand bobHand = new Hand("Bob");
        bobHand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        bobHand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        bobHand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        bobHand.addCard(new Card(Card.Value.FIVE, Card.Suit.CLUBS));
        bobHand.addCard(new Card(Card.Value.SEVEN, Card.Suit.HEARTS));
        EvaluationResult bob = new EvaluationResult(Hand.Rank.PAIR,bobHand, List.of(Card.Value.TWO));
        Hand charlieHand = new Hand("Charlie");
        charlieHand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        charlieHand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        charlieHand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        charlieHand.addCard(new Card(Card.Value.FIVE, Card.Suit.CLUBS));
        charlieHand.addCard(new Card(Card.Value.SEVEN, Card.Suit.HEARTS));
        EvaluationResult charlie = new EvaluationResult(Hand.Rank.PAIR,charlieHand, List.of(Card.Value.TWO));
        Hand danielHand = new Hand("Daniel");
        danielHand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        danielHand.addCard(new Card(Card.Value.FIVE, Card.Suit.HEARTS));
        danielHand.addCard(new Card(Card.Value.FOUR, Card.Suit.HEARTS));
        danielHand.addCard(new Card(Card.Value.FIVE, Card.Suit.CLUBS));
        danielHand.addCard(new Card(Card.Value.SEVEN, Card.Suit.HEARTS));
        EvaluationResult daniel = new EvaluationResult(Hand.Rank.PAIR,danielHand, List.of(Card.Value.TWO));

        PairEvaluator pairEvaluator = new PairEvaluator();
        String winner = pairEvaluator.evaluate(List.of(bob, adam, charlie, daniel));
        Assertions.assertThat(winner).isEqualTo("Adam");
    }

}
