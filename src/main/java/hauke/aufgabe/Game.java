package hauke.aufgabe;

import hauke.aufgabe.problem.EvaluationException;
import hauke.aufgabe.rules.*;
import lombok.NoArgsConstructor;

import java.util.Comparator;
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


    EvaluationResult evaluateHand(Hand hand) {
        for (EvaluationRule rule : rules) {
            if (rule.isApplicable(hand)) {
                return rule.evaluate(hand);
            }
        }
        return null;
    }

    public String evaluateGame(List<Hand> hands) throws EvaluationException {
        // evaluate hands and store results
        List<EvaluationResult> handEvaluationResults = hands.stream()
                .map(this::evaluateHand)
                .toList();

        // find max rank for game
        Hand.Rank maxRank = handEvaluationResults.stream().max(Comparator.comparingInt(evalResult -> evalResult.rank().ordinal()))
                .map(EvaluationResult::rank)
                .orElse(null);
        //
        if (maxRank == null) {
            return "max rank is null, cannot determine winner";
        }

        // check for winners
        List<EvaluationResult> winners = handEvaluationResults.stream()
                .filter(hand -> hand.rank() == maxRank).toList();

        if (winners.size() == 1) {
            return winners.getFirst().hand().getPlayerName();
        }

        // evaluate hands according to rank

        return null;
    }


}
