package hauke.aufgabe.rules;

import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

import java.util.function.Predicate;

public interface EvaluationRule<T> {


    /**
     * Returns a predicate that checks if the rule is applicable to the given hand.
     *
     * @return the predicate
     */
    Predicate<Hand> isApplicablePredicate();

    /**
     * Evaluates the given hand and returns the result.
     *
     * @param hand the hand to evaluate
     * @return the result of the evaluation
     */
    HandResult<T> evaluate(Hand hand);
}
