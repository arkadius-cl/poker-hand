package hauke.aufgabe.util;

import hauke.aufgabe.Card;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CardUtilTest {

    @Nested
    class IsOneSuit {
        @Test
        public void listOfCardsWithFlush_shouldReturnTrue() {
            List<Card> flushCards = List.of(
                    new Card(Card.Value.ACE, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS),
                    new Card(Card.Value.TEN, Card.Suit.HEARTS)
            );
            assertThat(CardUtils.isOneSuit(flushCards)).isTrue();
        }


        @Test
        public void listOfCardsWithoutFlush_shouldReturnFalse() {
            List<Card> nonFlushCards = List.of(
                    new Card(Card.Value.ACE, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS),
                    new Card(Card.Value.TEN, Card.Suit.DIAMONDS)
            );
            assertThat(CardUtils.isOneSuit(nonFlushCards)).isFalse();
        }
    }

    @Nested
    class GetValuesByCount {

        @Test
        public void listOfCardsWithOnePair_shouldReturnListWithPairValue() {
            List<Card> pairCards = List.of(
                    new Card(Card.Value.ACE, Card.Suit.HEARTS),
                    new Card(Card.Value.ACE, Card.Suit.DIAMONDS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS)
            );
            List<Card.Value> pairValues = CardUtils.getValuesByCount(pairCards, 2);
            assertThat(pairValues).containsExactlyInAnyOrder(Card.Value.ACE);
        }

        @Test
        public void listOfCardsWithTwoPairs_shouldReturnListWithPairValues() {
            List<Card> twoPairsCards = List.of(
                    new Card(Card.Value.ACE, Card.Suit.HEARTS),
                    new Card(Card.Value.ACE, Card.Suit.DIAMONDS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.DIAMONDS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS)
            );
            List<Card.Value> pairValues = CardUtils.getValuesByCount(twoPairsCards, 2);
            assertThat(pairValues).containsExactlyInAnyOrder(Card.Value.ACE, Card.Value.KING);
        }

        @Test
        public void listOfCardsWithoutPairs_shouldReturnEmptyList() {
            List<Card> noPairCards = List.of(
                    new Card(Card.Value.ACE, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.DIAMONDS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS),
                    new Card(Card.Value.TEN, Card.Suit.HEARTS)
            );
            List<Card.Value> pairValues = CardUtils.getValuesByCount(noPairCards, 2);
            assertThat(pairValues).isEmpty();
        }
    }

    @Nested
    class GetHighestValue {
        @Test
        public void listOfCardsWithHighestValueAce_shouldReturnAce() {
            List<Card> aceCards = List.of(
                    new Card(Card.Value.ACE, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.DIAMONDS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS),
                    new Card(Card.Value.TEN, Card.Suit.HEARTS)
            );
            Card.Value highestValue = CardUtils.getHighestValue(aceCards);
            assertThat(highestValue).isEqualTo(Card.Value.ACE);
        }

        @Test
        public void listOfCardsWithHighestValueKing_shouldReturnKing() {
            List<Card> kingCards = List.of(
                    new Card(Card.Value.TWO, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.DIAMONDS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS)
            );
            Card.Value highestValue = CardUtils.getHighestValue(kingCards);
            assertThat(highestValue).isEqualTo(Card.Value.KING);
        }

        @Test
        public void emptyListOfCards_shouldReturnNull() {
            List<Card> emptyCards = List.of();
            Card.Value highestValue = CardUtils.getHighestValue(emptyCards);
            assertThat(highestValue).isNull();
        }
    }

    @Nested
    class GroupByValue {
        @Test
        public void listOfCards_shouldReturnMapWithValuesAndCards() {
            List<Card> cards = List.of(
                    new Card(Card.Value.ACE, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.DIAMONDS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS)
            );
            Map<Card.Value, List<Card>> groupedCards = CardUtils.groupByValue(cards);
            assertThat(groupedCards).containsOnlyKeys(Card.Value.ACE, Card.Value.KING, Card.Value.QUEEN, Card.Value.JACK);
            assertThat(groupedCards.get(Card.Value.ACE)).containsExactly(new Card(Card.Value.ACE, Card.Suit.HEARTS));
            assertThat(groupedCards.get(Card.Value.KING)).containsExactlyInAnyOrder(
                    new Card(Card.Value.KING, Card.Suit.DIAMONDS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS)
            );
            assertThat(groupedCards.get(Card.Value.QUEEN)).containsExactly(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
            assertThat(groupedCards.get(Card.Value.JACK)).containsExactly(new Card(Card.Value.JACK, Card.Suit.HEARTS));
        }
    }

    @Nested
    class GetStraightHighestValue {
        @Test
        public void listOfCardsWithStraight_shouldReturnHighestValue() {
            List<Card> straightCards = List.of(
                    new Card(Card.Value.TWO, Card.Suit.HEARTS),
                    new Card(Card.Value.THREE, Card.Suit.DIAMONDS),
                    new Card(Card.Value.FOUR, Card.Suit.HEARTS),
                    new Card(Card.Value.FIVE, Card.Suit.HEARTS),
                    new Card(Card.Value.SIX, Card.Suit.HEARTS)
            );
            Card.Value highestValue = CardUtils.getStraightHighestValue(straightCards);
            assertThat(highestValue).isEqualTo(Card.Value.SIX);
        }

        @Test
        public void listOfCardsWithAceLowStraight_shouldReturnHighestValue() {
            List<Card> aceLowStraightCards = List.of(
                    new Card(Card.Value.TWO, Card.Suit.HEARTS),
                    new Card(Card.Value.THREE, Card.Suit.DIAMONDS),
                    new Card(Card.Value.FOUR, Card.Suit.HEARTS),
                    new Card(Card.Value.FIVE, Card.Suit.HEARTS),
                    new Card(Card.Value.ACE, Card.Suit.HEARTS)
            );
            Card.Value highestValue = CardUtils.getStraightHighestValue(aceLowStraightCards);
            assertThat(highestValue).isEqualTo(Card.Value.FIVE);
        }

        @Test
        public void listOfCardsWithAceHighStraight_shouldReturnHighestValue() {
            List<Card> aceHighStraightCards = List.of(
                    new Card(Card.Value.TEN, Card.Suit.HEARTS),
                    new Card(Card.Value.JACK, Card.Suit.DIAMONDS),
                    new Card(Card.Value.QUEEN, Card.Suit.HEARTS),
                    new Card(Card.Value.KING, Card.Suit.HEARTS),
                    new Card(Card.Value.ACE, Card.Suit.HEARTS)
            );
            Card.Value highestValue = CardUtils.getStraightHighestValue(aceHighStraightCards);
            assertThat(highestValue).isEqualTo(Card.Value.ACE);
        }

        @Test
        public void listOfCardsWithoutStraight_shouldReturnNull() {
            List<Card> noStraightCards = List.of(
                    new Card(Card.Value.TWO, Card.Suit.HEARTS),
                    new Card(Card.Value.THREE, Card.Suit.DIAMONDS),
                    new Card(Card.Value.FOUR, Card.Suit.HEARTS),
                    new Card(Card.Value.FIVE, Card.Suit.HEARTS),
                    new Card(Card.Value.SEVEN, Card.Suit.HEARTS)
            );
            Card.Value highestValue = CardUtils.getStraightHighestValue(noStraightCards);
            assertThat(highestValue).isNull();
        }
    }


}
