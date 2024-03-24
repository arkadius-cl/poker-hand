package hauke.aufgabe.ranks;

import hauke.aufgabe.rules.EvaluationResult;

import java.util.List;

public interface RankEvaluator {
    String evaluate(List<EvaluationResult> handResults);
}
