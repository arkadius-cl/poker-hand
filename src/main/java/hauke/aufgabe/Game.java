package hauke.aufgabe;

import hauke.aufgabe.rules.*;

import java.util.ArrayList;
import java.util.List;


public class Game {

    List<PokerRule> rules = new ArrayList<>();

    public Game() {
        loadRules();
    }

    private void loadRules() {
        rules.add(new StraightFlushRule());
        rules.add(new FourOfKindRule());
        rules.add(new FullHouseRule());
        rules.add(new FlushRule());
        rules.add(new StraightRule());
        rules.add(new ThreeOfKindRule());
        rules.add(new TwoPairsRule());
        rules.add(new PairRule());
        rules.add(new HighCardRule());
    }

    public <T> HandResult<T> evaluateHand(Hand hand) {
        for (PokerRule rule : rules) {
            if (rule.applicable(hand)) {
                return rule.rank(hand);
            }
        }
        return null;
    }


}
