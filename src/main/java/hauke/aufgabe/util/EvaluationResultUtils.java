package hauke.aufgabe.util;

import hauke.aufgabe.Card;
import hauke.aufgabe.rules.EvaluationResult;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    /**
     * Removes cards from the evaluation results that match the specified filter.
     *
     * @param handResults   the list of evaluation results
     * @param removalFilter the filter that determines which cards to remove
     * @return a new list of evaluation results with the specified cards removed
     */
    public static List<EvaluationResult> removeFromResults(List<EvaluationResult> handResults, Function<EvaluationResult, Predicate<Card.Value>> removalFilter) {

        return handResults
                .stream()
                .map(evalResult -> {
                    Predicate<Card.Value> filter = removalFilter.apply(evalResult);
                    return new EvaluationResult(evalResult.rank(), evalResult.hand(), createNewValuesFromHandCardsByFilter(filter).apply(evalResult));
                })
                .toList();
    }

    /**
     * Creates a new list of card values from the hand cards of an evaluation result by applying a filter.
     *
     * @param removalFilter the filter that determines which cards to remove
     * @return a function that creates a new list of card values by applying the filter
     */
    private static Function<EvaluationResult, List<Card.Value>> createNewValuesFromHandCardsByFilter(Predicate<Card.Value> removalFilter) {
        return evalResult -> evalResult.hand().getCards().stream()
                .map(Card::getValue)
                .filter(removalFilter)
                .sorted(Card.Value::compareTo)
                .toList();
    }

    public static List<String> findHighestValueWinnersForIndex(List<EvaluationResult> handResults, int index) {
        Card.Value highestCard = EvaluationResultUtils.getHighestCardForIndex(handResults, EvaluationResult::values, index);
        return handResults.stream()
                .filter(handResult -> handResult.values().get(index) == highestCard)
                .map(evalResult -> evalResult.hand().getPlayerName())
                .collect(Collectors.toList());
    }

    public static String findHighestValueWinner(List<EvaluationResult> handResults) {
        // assume, all results have the same number of cards in value list
        int size = handResults.getFirst().values().size();
        List<String> winners = List.of();
        for (int i = 0; i < size; i++) {
            winners = EvaluationResultUtils.findHighestValueWinnersForIndex(handResults, i);
            if (winners.size() == 1) {
                return winners.getFirst();
            }
        }
        return String.join(", ", winners);
    }


}
