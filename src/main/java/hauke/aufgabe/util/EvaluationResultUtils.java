package hauke.aufgabe.util;

import hauke.aufgabe.Card;
import hauke.aufgabe.rules.EvaluationResult;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public final class EvaluationResultUtils {

    private EvaluationResultUtils() {
    }

    /**
     * Returns the highest card value at a specified index from a list of evaluation results.
     *
     * @param result         the list of evaluation results
     * @param valuesSupplier a function that returns the list of card values for each evaluation result
     * @param index          the index of the card value to retrieve
     * @return the highest card value at the specified index, or null if the index is out of range
     */
    public static Card.Value getHighestCardForIndex(List<EvaluationResult> result, Function<EvaluationResult, List<Card.Value>> valuesSupplier, int index) {
        return result.stream()
                .map(valuesSupplier)
                .map(values -> values.get(index))
                .max(Comparator.comparingInt(Card.Value::ordinal))
                .orElse(null);
    }


}
