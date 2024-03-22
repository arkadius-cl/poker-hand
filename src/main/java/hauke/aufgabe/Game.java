package hauke.aufgabe;

import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.EvaluationResult;
import hauke.aufgabe.rules.*;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@NoArgsConstructor
public class Game {

    private final List<EvaluationRule> rules = List.of(
            new StraightFlushRule(),
            new FourOfKindRule(),
            new FullHouseRule(),
            new FlushRule(),
            new StraightRule(),
            new ThreeOfKindRule(),
            new TwoPairsRule(),
            new PairRule(),
            new HighCardRule()
    );


    EvaluationResult evaluateHand(Hand hand) {
        for (EvaluationRule rule : rules) {
            if (rule.isApplicable(hand)) {
                return rule.evaluate(hand);
            }
        }
        return null;
    }

    public String evaluateGame(List<Hand> hands) throws EvaluationException {
        // evaluate individual hands
        Map<String, EvaluationResult> evaluationResult = hands.stream().collect(Collectors.toMap(Hand::getName, this::evaluateHand));
        Hand.Rank maxRank = Collections.max(evaluationResult.values(), Comparator.comparing(EvaluationResult::rank)).rank();
        // check if there are multiple winners
        Map<String, EvaluationResult> winners = evaluationResult.entrySet().stream()
                .filter(entry -> entry.getValue().rank() == maxRank)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (winners.size() == 1) {
            return winners.keySet().iterator().next();
        }
        // evaluate hands according to rank

        return "dupa";
    }


}
