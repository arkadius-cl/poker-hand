package hauke.aufgabe.rules;

import hauke.aufgabe.Hand;
import hauke.aufgabe.HandResult;

public interface PokerRule<T> {

    boolean applicable(Hand hand);
    HandResult<T> rank(Hand hand);
}
