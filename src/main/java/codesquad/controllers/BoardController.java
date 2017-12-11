package codesquad.controllers;

import codesquad.model.Account;
import codesquad.repository.AccountRepository;
import codesquad.security.Exceptions.NoRegisteredUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/newboard")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getBoardPage(Authentication authentication, Model model, HttpSession session) {
        log.debug("requested user's username is : {}" , authentication.getName());
        Account account = accountRepository.findByUserId(authentication.getName()).orElseThrow(() -> new NoRegisteredUserException("no user found in DB!"));

        model.addAttribute("decks", account.getDecks());

        return "boardTemplate";
    }
}
