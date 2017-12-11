package codesquad.controllers;

import codesquad.dto.CardDto;
import codesquad.model.*;
import codesquad.repository.AccountRepository;
import codesquad.security.Exceptions.NoRegisteredUserException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/service")
public class ServiceApiController {

    private static final Logger log = LoggerFactory.getLogger(ServiceApiController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/decks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Deck> returnUserDecks(Authentication authentication, Principal principal) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        log.debug("type of authentiction token is : {}", authentication.getPrincipal().getClass().getName());
        log.debug("type of principal token is : {}" , principal.getClass());
        return accountRepository.findByUserId(authentication.getName()).orElseThrow(() -> new NoSuchElementException("fuck!")).getDecks();

    }

    @PostMapping("/decks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Deck saveUserDecks(Authentication authentication, @RequestBody String deckData) {
        log.debug("posted deck information : {}", deckData);
        Deck deck = null;
        try {
            deck = objectMapper.readValue(deckData, Deck.class);

        } catch (Exception e) {
            return new ErrorDeck();
        }
        //log.debug("posted deck information; {}" , deck);

        Account account = accountRepository.findByUserId(authentication.getName()).orElseThrow(() -> new NoRegisteredUserException("회원 정보가 없습니다."));
        account.addDeck(deck);

        accountRepository.save(account);
        return deck;
    }

    @PostMapping("/newcards")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Card saveNewCard(Authentication authentication, @RequestBody String cardData) {
        log.debug("POSTed card information : {}", cardData);
        CardDto cardinfo;

        try {
            cardinfo = objectMapper.readValue(cardData, CardDto.class);

        } catch(Exception e) {

        }

        return null;
    }

    @GetMapping("/authinfo")
    public String returnUserAuthInfo(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "shit! you are not authenticated!";
        }
        return authentication.getName() + ". your decks count is : " + accountRepository.findByUserId(authentication.getName()).get().getDecks().size();
    }
}
