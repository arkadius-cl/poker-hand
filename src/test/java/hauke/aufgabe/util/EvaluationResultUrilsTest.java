package hauke.aufgabe.util;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;
import hauke.aufgabe.rules.EvaluationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class EvaluationResultUrilsTest {

    @Test
    public void  twoEvaluationResults_shouldReturnTheHigherOne() {
        EvaluationResult adam = new EvaluationResult(Hand.Rank.PAIR, new Hand(), List.of(
                Card.Value.TWO, Card.Value.THREE, Card.Value.NINE, Card.Value.FIVE, Card.Value.SIX));
        EvaluationResult bob = new EvaluationResult(Hand.Rank.PAIR, new Hand(), List.of(
                Card.Value.TWO, Card.Value.FIVE, Card.Value.FOUR, Card.Value.FIVE, Card.Value.SEVEN));

        Card.Value first = EvaluationResultUtils.getHighestCardForIndex(List.of(adam, bob), EvaluationResult::values,0);
        assertThat(first).isEqualTo(Card.Value.TWO);
        Card.Value second = EvaluationResultUtils.getHighestCardForIndex(List.of(adam, bob), EvaluationResult::values,1);
        assertThat(second).isEqualTo(Card.Value.FIVE);
        Card.Value third = EvaluationResultUtils.getHighestCardForIndex(List.of(adam, bob), EvaluationResult::values,2);
        assertThat(third).isEqualTo(Card.Value.NINE);

    }
}
