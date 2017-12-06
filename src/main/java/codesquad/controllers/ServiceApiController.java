package codesquad.controllers;

import codesquad.model.Account;
import codesquad.model.Deck;
import codesquad.model.ErrorDeck;
import codesquad.model.SecurityAccount;
import codesquad.repository.AccountRepository;
import codesquad.security.NoRegisteredUserException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/service")
public class ServiceApiController {

    private static final Logger log = LoggerFactory.getLogger(ServiceApiController.class);

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/decks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Deck> returnUserDecks(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return accountRepository.findByUserId(authentication.getName()).orElseThrow(() -> new NoSuchElementException("fuck!")).getDecks();

    }

    @PostMapping("/decks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Deck saveUserDecks(Authentication authentication, @RequestBody String deckData) {
        log.debug("posted deck information : {}", deckData);
        Deck deck = null;
        try {
            deck = new ObjectMapper().readValue(deckData, Deck.class);

        } catch (Exception e) {
            return new ErrorDeck();
        }
        //log.debug("posted deck information; {}" , deck);

        Account account = accountRepository.findByUserId(authentication.getName()).orElseThrow(() -> new NoRegisteredUserException("회원 정보가 없습니다."));
        account.addDeck(deck);

        accountRepository.save(account);
        return deck;
    }

    @GetMapping("/authinfo")
    public String returnUserAuthInfo(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "shit! you are not authenticated!";
        }
        return authentication.getName() + ". your decks count is : " + accountRepository.findByUserId(authentication.getName()).get().getDecks().size();
    }
}
