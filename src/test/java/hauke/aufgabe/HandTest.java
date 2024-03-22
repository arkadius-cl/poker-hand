package hauke.aufgabe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.stream.IntStream;

public class HandTest {

    @Test
    public void addNULLCard_shouldThrowIllegalArgumentException() {
        Hand hand = new Hand();
        Assertions.assertThatThrownBy(() -> hand.addCard(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addSameCardTwice_shouldThrowIllegalArgumentException() {
        Hand hand = new Hand();
        Card card = new Card();
        hand.addCard(card);
        Assertions.assertThatThrownBy(() -> hand.addCard(card)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addSixCards_shouldThrowIllegalArgumentException() {
        Hand hand = new Hand();
        for (int i = 0; i < 5; i++) {
            hand.addCard(new Card());
        }
        Assertions.assertThatThrownBy(() -> hand.addCard(new Card())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createHudredHandsWithoutName_shouldNotHaveSameName() {
        IntStream
                .range(0, 100)
                .mapToObj(i -> new Hand())
                .forEach(hand -> Assertions.assertThat(hand).matches((givenHand) -> StringUtils.isNotBlank(givenHand.getName())));
    }

     @Test
    public void createTwoHandsWithDifferentNames_ShouldBeDifferent() {
        Hand hand1 = new Hand("Hand1");
        Hand hand2 = new Hand("Hand2");
        Assertions.assertThat(hand1.getName()).isEqualTo("Hand1");
        Assertions.assertThat(hand2.getName()).isEqualTo("Hand2");
        Assertions.assertThat(hand1.getName()).isNotEqualTo(hand2.getName());
    }
}
