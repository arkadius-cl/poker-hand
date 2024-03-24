package hauke.aufgabe.rules;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

import java.util.List;


/**
 * Represents the result of an evaluation of a hand.
 */
public record EvaluationResult(Hand.Rank rank, Hand hand, List<Card.Value> values) {

    public EvaluationResult {
        if (rank == null) {
            throw new IllegalArgumentException("Rank must not be null.");
        }
        if (hand == null) {
            throw new IllegalArgumentException("Hand must not be null.");
        }
        if (values == null) {
            throw new IllegalArgumentException("Values must not be null.");
        }
    }

    @Override
    public String toString() {
        return "EvaluationResult{" +
                "rank=" + rank +
                ", hand=" + hand +
                ", values=" + values +
                '}';
    }


}
