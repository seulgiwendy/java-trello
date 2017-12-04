package codesquad.controllers;

import codesquad.model.Account;
import codesquad.model.Deck;
import codesquad.model.SecurityAccount;
import codesquad.repository.AccountRepository;
import codesquad.security.NoRegisteredUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/service")
public class ServiceApiController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/decks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Deck> returnUserDecks(Authentication authentication) {
        //TODO check user's authentication info and retrieve the deck he or she has.
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return accountRepository.findByUserId(authentication.getName()).orElseThrow(() -> new NoSuchElementException("fuck!")).getDecks();

    }

    @PostMapping("/decks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void saveUserDecks(Authentication authentication, Deck deck) {
        Account account = accountRepository.findByUserId(authentication.getName()).orElseThrow(() -> new NoRegisteredUserException("회원 정보가 없습니다."));
        account.addDeck(deck);

        accountRepository.save(account);
    }

    @GetMapping("/authinfo")
    public String returnUserAuthInfo(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "shit! you are not authenticated!";
        }
        return authentication.getName() + ". your decks count is : " + accountRepository.findByUserId(authentication.getName()).get().getDecks().size();
    }
}
