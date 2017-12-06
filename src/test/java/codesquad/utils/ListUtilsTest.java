package codesquad.utils;

import codesquad.model.Card;
import codesquad.model.Deck;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ListUtilsTest {

    private ListUtils<Deck> listUtils;
    private List<Deck> decks = Lists.newArrayList();

    @Before
    public void setUp() {
        this.listUtils = new ListUtils<>();
        IntStream.range(0, 6).forEach(i ->  this.decks.add(i, new Deck()));
    }

    @Test
    public void ELEMENT_UPDATE_SUCCESS() {
        Deck deck = new Deck();
        deck.setCard(new Card());

        listUtils.update(this.decks, deck);

        assertNotNull(this.decks.get(0).getCards());

    }

}