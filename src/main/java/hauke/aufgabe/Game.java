package hauke.aufgabe;

import hauke.aufgabe.result.EvaluationResult;
import hauke.aufgabe.rules.*;
import lombok.NoArgsConstructor;

import java.util.List;


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


    public EvaluationResult evaluateHand(Hand hand) {
        for (EvaluationRule rule : rules) {
            if (rule.isApplicable(hand)) {
                return rule.evaluate(hand);
            }
        }
        return null;
    }


}
