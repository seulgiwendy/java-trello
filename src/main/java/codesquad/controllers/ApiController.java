package codesquad.controllers;

import codesquad.dto.CardDto;
import codesquad.model.Card;
import codesquad.model.Deck;
import codesquad.model.User;
import codesquad.repository.CardRepository;
import codesquad.repository.DeckRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/legacy")
@RestController
public class ApiController {

    @Autowired
    DeckRepository deckRepository;

    @Autowired
    CardRepository cardRepository;

    @PostMapping("/decks/new")
    public Deck setNewDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    @GetMapping("/decks/{id}")
    public Deck getDeck(@PathVariable long id) {
        return deckRepository.findOne(id);
    }

    @PostMapping("/cards/new")
    public Card setNewCard(CardDto cardDto) {
        Card c = new Card();
        c.setContent(cardDto.getContents());
        c.setTitle(cardDto.getTitle());

        Deck parentDeck = deckRepository.findOne(cardDto.getIndex());

        c.setDeck(parentDeck);

        return cardRepository.save(c);



    }


}
