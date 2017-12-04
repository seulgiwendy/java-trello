package codesquad.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(table = "deck")
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
}
