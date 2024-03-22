package hauke.aufgabe;

import hauke.aufgabe.rules.*;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class Game {

    private final List<PokerRule> rules = List.of(
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


    public <T> HandResult<T> evaluateHand(Hand hand) {
        for (PokerRule rule : rules) {
            if (rule.applicable(hand)) {
                return rule.rank(hand);
            }
        }
        return null;
    }


}
