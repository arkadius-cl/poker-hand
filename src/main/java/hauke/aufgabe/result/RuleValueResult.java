package hauke.aufgabe.result;

import hauke.aufgabe.Card;
import hauke.aufgabe.Hand;

public record RuleValueResult(Hand.Rank rank, Card.Value value) implements RuleEvaluationResult {
}
