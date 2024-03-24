package hauke.aufgabe;

import hauke.aufgabe.ranks.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Hand {

    @Getter
    @RequiredArgsConstructor
    public enum Rank {
        HIGH_CARD(HighCardEvaluator::new),
        PAIR(PairEvaluator::new),
        TWO_PAIR(TwoPairEvaluator::new),
        THREE_OF_KIND(ThreeOfKindEvaluator::new),
        STRAIGHT(StraightEvaluator::new),
        FLUSH(FlushEvaluator::new),
        FULL_HOUSE(FullHouseEvaluator::new),
        FOUR_OF_KIND(FourOfKind::new),
        STRAIGHT_FLUSH(StraightEvaluator::new);

        private final Supplier<RankEvaluator> evaluator;
    }

    @Getter
    private final String playerName;

    public Hand() {
        this.playerName = "Unnamed " + ((int) (Math.random() * 1000));
    }

    public Hand(String name) {
        this.playerName = name;
    }

    private final List<Card> cards = new ArrayList<>();

    /**
     * Returns the cards in the hand.
     *
     * @return the cards in the hand (immutable copy)
     */
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    /**
     * Adds a card to the hand.
     *
     * @param card the card to add
     * @throws IllegalArgumentException if the card is null, the hand is full or the card is already in the hand
     */
    public void addCard(Card card) {
        if (Objects.isNull(card)) {
            throw new IllegalArgumentException("Card must not be null");
        }
        if (cards.size() >= 5) {
            throw new IllegalArgumentException("Hand is full");
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException("Card is already in hand");
        }
        cards.add(card);
    }

}
