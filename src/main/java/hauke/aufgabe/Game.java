package hauke.aufgabe;

import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.EvaluationResult;
import hauke.aufgabe.rules.*;
import lombok.NoArgsConstructor;

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
        Map<String, EvaluationResult> results = hands.stream().collect(Collectors.toMap(Hand::getName, this::evaluateHand));
        // check if there is a winner


        // check if there are multiple winners

        // evaluate hands according to rank
        return "dupa";
    }


}
