package codesquad.controllers;

import codesquad.dto.CardDto;
import codesquad.model.Account;
import codesquad.model.Card;
import codesquad.model.Deck;
import codesquad.model.SecurityAccount;
import codesquad.repository.AccountRepository;
import codesquad.repository.CardRepository;
import codesquad.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@RequestMapping("/api/legacy")
@RestController
public class ApiController {

    @Autowired
    DeckRepository deckRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/user")
    public String getUserDetails(Authentication authentication) {
        if (authentication.getPrincipal() instanceof SecurityAccount) {
            SecurityAccount loginUser = (SecurityAccount)authentication.getPrincipal();
            return String.format("your userid is : %s, your name is : %s", loginUser.getAccount().getUserId(), loginUser.getAccount().getName());

        }
        return String.format("your username is %s, your details are as follows : %s" , authentication.getPrincipal(), authentication.getDetails());
    }

    @GetMapping("/exception")
    public String throwException() {
        throw new RuntimeException("ssibal!");
    }

    @DeleteMapping("/user")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/decks/new")
    public Deck setNewDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    @GetMapping("/decks/{id}")
    public Deck getDeck(@PathVariable long id, Pageable pageable) {
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
