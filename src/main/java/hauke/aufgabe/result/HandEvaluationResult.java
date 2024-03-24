package hauke.aufgabe.result;


import hauke.aufgabe.rules.EvaluationResult;

public record HandEvaluationResult(String name, EvaluationResult result) implements Comparable<HandEvaluationResult> {

    @Override
    public int compareTo(HandEvaluationResult o) {
        return Integer.compare(result.rank().ordinal(), o.result.rank().ordinal());
    }
}
