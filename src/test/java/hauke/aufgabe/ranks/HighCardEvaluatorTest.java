package hauke.aufgabe.ranks;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.result.HandEvaluationResult;
import hauke.aufgabe.result.RuleValuesListResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HighCardEvaluatorTest {

    @Test
    public void twoHandEvaluationResults_shouldReturnWinner() {
        HandEvaluationResult adam = new HandEvaluationResult("Adam", new RuleValuesListResult(Hand.Rank.HIGH_CARD, List.of(Card.Value.ACE, Card.Value.KING, Card.Value.QUEEN, Card.Value.JACK, Card.Value.TEN)));
        HandEvaluationResult bob = new HandEvaluationResult("Bob", new RuleValuesListResult(Hand.Rank.HIGH_CARD, List.of(Card.Value.ACE, Card.Value.QUEEN, Card.Value.QUEEN, Card.Value.JACK, Card.Value.NINE)));

        HighCardEvaluator highCardEvaluator = new HighCardEvaluator();
        String winner = highCardEvaluator.evaluate(List.of(adam, bob));
        Assertions.assertThat(winner).isEqualTo("Adam");
    }

    @Test
    public void fourHandEvaluationResults_allCardsExceptLastAreSame_shouldReturnWinner2() {
        HandEvaluationResult adam = new HandEvaluationResult("Adam", new RuleValuesListResult(Hand.Rank.HIGH_CARD, List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.TWO)));
        HandEvaluationResult bob = new HandEvaluationResult("Bob", new RuleValuesListResult(Hand.Rank.HIGH_CARD, List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.THREE)));
        HandEvaluationResult charlie = new HandEvaluationResult("Charlie", new RuleValuesListResult(Hand.Rank.HIGH_CARD, List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.TWO)));
        HandEvaluationResult daniel = new HandEvaluationResult("Daniel", new RuleValuesListResult(Hand.Rank.HIGH_CARD, List.of(Card.Value.JACK, Card.Value.TEN, Card.Value.NINE, Card.Value.EIGHT, Card.Value.SIX)));

        HighCardEvaluator highCardEvaluator = new HighCardEvaluator();
        String winner = highCardEvaluator.evaluate(List.of(bob, adam, charlie, daniel));
        Assertions.assertThat(winner).isEqualTo("Daniel");
    }
}
