package hauke.aufgabe.result;


public record HandEvaluationResult(String name, RuleEvaluationResult result) implements Comparable<HandEvaluationResult> {

    @Override
    public int compareTo(HandEvaluationResult o) {
        return Integer.compare(result.rank().ordinal(), o.result.rank().ordinal());
    }
}
