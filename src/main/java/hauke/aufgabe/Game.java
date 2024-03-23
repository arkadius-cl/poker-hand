package hauke.aufgabe;

import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.result.RuleEvaluationResult;
import hauke.aufgabe.result.HandEvaluationResult;
import hauke.aufgabe.rules.*;
import lombok.NoArgsConstructor;

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


    RuleEvaluationResult evaluateHand(Hand hand) {
        for (EvaluationRule rule : rules) {
            if (rule.isApplicable(hand)) {
                return rule.evaluate(hand);
            }
        }
        return null;
    }

    public String evaluateGame(List<Hand> hands) throws EvaluationException {
        // evaluate individual hands
        List<HandEvaluationResult> handsEvaluationResult = hands.stream()
                .map(hand -> new HandEvaluationResult(hand.getPlayerName(), evaluateHand(hand)))
                .toList();

        // find max rank for game
        Hand.Rank maxRank = handsEvaluationResult.stream().max(Comparator.comparingInt(o -> o.result().rank().ordinal()))
                .map(gameResult -> gameResult.result().rank())
                .orElseThrow(() -> new EvaluationException("No hands to evaluate"));

        // check for winners
        Map<String, RuleEvaluationResult> winners = handsEvaluationResult.stream()
                .filter(hand -> hand.result().rank() == maxRank)
                .collect(Collectors.toMap(HandEvaluationResult::name, HandEvaluationResult::result));

        if (winners.size() == 1) {
            return winners.keySet().iterator().next();
        }

        // evaluate hands according to rank

        return null;
    }


}
