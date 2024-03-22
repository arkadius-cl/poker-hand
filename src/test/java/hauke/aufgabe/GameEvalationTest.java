package hauke.aufgabe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameEvalationTest {

    @Test
    public void twoHandsOneWithTwoPairsSecondWithFullHouse_shouldReturnSecondHandAsWinner() {
        Hand antonyHand = new Hand("Antony");
        antonyHand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        antonyHand.addCard(new Card(Card.Value.KING, Card.Suit.DIAMONDS));
        antonyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        antonyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.SPADES));
        antonyHand.addCard(new Card(Card.Value.TWO, Card.Suit.HEARTS));
        Hand bettyHand = new Hand("Betty");
        bettyHand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        bettyHand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        bettyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        bettyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.SPADES));
        bettyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.CLUBS));
        List<Hand> hands = List.of(antonyHand, bettyHand);
        Game game = new Game();
        String winner = game.evaluateGame(hands);
        Assertions.assertThat(winner).isEqualTo("Betty");
    }

    @Test
    public void twoHandsWithFullHouse_shouldReturnOneWinner(){
        Hand antonyHand = new Hand("Antony");
        antonyHand.addCard(new Card(Card.Value.TWO, Card.Suit.CLUBS));
        antonyHand.addCard(new Card(Card.Value.TWO, Card.Suit.DIAMONDS));
        antonyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.HEARTS));
        antonyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.SPADES));
        antonyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.CLUBS));
        Hand bettyHand = new Hand("Betty");
        bettyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.CLUBS));
        bettyHand.addCard(new Card(Card.Value.QUEEN, Card.Suit.DIAMONDS));
        bettyHand.addCard(new Card(Card.Value.ACE, Card.Suit.HEARTS));
        bettyHand.addCard(new Card(Card.Value.ACE, Card.Suit.SPADES));
        bettyHand.addCard(new Card(Card.Value.ACE, Card.Suit.CLUBS));
        List<Hand> hands = List.of(antonyHand, bettyHand);
        Game game = new Game();
        String winner = game.evaluateGame(hands);
        Assertions.assertThat(winner).isEqualTo("Betty");

    }
}

