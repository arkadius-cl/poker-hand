package hauke.aufgabe.rules;

import hauke.aufgabe.Hand;
import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.RuleEvaluationResult;

import java.util.Optional;
import java.util.function.Predicate;

public interface EvaluationRule {

    /**
     * Checks if the rule is applicable to the given hand.
     *
     * @param hand the hand to check
     * @return true if the rule is applicable, false otherwise
     */
    default boolean isApplicable(Hand hand) {
        return Optional
                .ofNullable(hand)
                .filter(suppliedHand -> suppliedHand.getCards().size() == 5)
                .filter(applicationPredicate())
                .isPresent();
    }

    /**
     * Returns the predicate that should be used to check if the rule is applicable.
     *
     * @return the predicate
     */
    Predicate<Hand> applicationPredicate();

    /**
     * Evaluates the given hand and returns the result.
     *
     * @param hand the hand to evaluate
     * @return the result of the evaluation
     * @throws EvaluationException if the evaluation fails
     */
    RuleEvaluationResult evaluate(Hand hand) throws EvaluationException;


}
