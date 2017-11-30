package codesquad.controllers;

import codesquad.model.Account;
import codesquad.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class SigninController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(SigninController.class);


    @PostMapping("/signup")
    public String joinAccount(Account account, HttpServletRequest req) {
        String originUrl = req.getHeader("Referer");
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        account.setRole("USER");
        accountRepository.save(account);
        return "redirect:" + originUrl;
    }

}
