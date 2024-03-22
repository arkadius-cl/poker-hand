package hauke.aufgabe.result;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

import java.util.List;

public record ValuesListResult(Hand.Rank rank, List<Card.Value> value) implements EvaluationResult {
}
