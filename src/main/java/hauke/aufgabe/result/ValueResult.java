package hauke.aufgabe.result;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

public record ValueResult(Hand.Rank rank, Card.Value value) implements EvaluationResult {
}
