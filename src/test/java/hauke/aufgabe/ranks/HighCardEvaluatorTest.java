package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.rules.EvaluationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HighCardEvaluatorTest {

    @Test
    public void twoHandEvaluationResults_shouldReturnWinner() {
        EvaluationResult adam = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Adam"), List.of(Card.Value.ACE, Card.Value.KING, Card.Value.QUEEN, Card.Value.JACK, Card.Value.TEN));
        EvaluationResult bob = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Bob"), List.of(Card.Value.ACE, Card.Value.QUEEN, Card.Value.QUEEN, Card.Value.JACK, Card.Value.NINE));

        HighCardEvaluator highCardEvaluator = new HighCardEvaluator();
        String winner = highCardEvaluator.evaluate(List.of(adam, bob));
        Assertions.assertThat(winner).isEqualTo("Adam");
    }

    @Test
    public void fourHandEvaluationResults_allCardsExceptLastAreSame_shouldReturnWinner2() {
        EvaluationResult adam = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Adam"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.TWO));
        EvaluationResult bob = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Bob"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.THREE));
        EvaluationResult charlie = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Charlie"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.TWO));
        EvaluationResult daniel = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Daniel"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.SIX));

        HighCardEvaluator highCardEvaluator = new HighCardEvaluator();
        String winner = highCardEvaluator.evaluate(List.of(bob, adam, charlie, daniel));
        Assertions.assertThat(winner).isEqualTo("Daniel");
    }

    @Test
    public void fourHandEvaluationResults_allCardsEsceptLastAreSame_shouldReturnTwoWinner() {
        EvaluationResult adam = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Adam"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.TWO));
        EvaluationResult bob = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Bob"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.THREE));
        EvaluationResult charlie = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Charlie"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.TWO));
        EvaluationResult daniel = new EvaluationResult(Hand.Rank.HIGH_CARD, new Hand("Daniel"), List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.THREE));

        HighCardEvaluator highCardEvaluator = new HighCardEvaluator();
        String winner = highCardEvaluator.evaluate(List.of(bob, adam, charlie, daniel));
        Assertions.assertThat(winner).isEqualTo("Bob, Daniel");
    }
}
