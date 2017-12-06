package codesquad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
public class Deck {

    @JsonIgnore
    private static final String DECK_ADDR_PREFIX = "/api/service/decks/newcard";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "deck")
    private List<Card> cards = Lists.newArrayList();

    public Deck() {

    }

    public Deck(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cards=" + cards +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equal(title, deck.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
