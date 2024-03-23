package hauke.aufgabe.ranks;

import hauke.aufgabe.result.HandEvaluationResult;

import java.util.List;

public interface RankEvaluator {
    String evaluate(List<HandEvaluationResult> handResults);
}
