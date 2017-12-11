package codesquad.controllers;

import codesquad.model.Account;
import codesquad.repository.AccountRepository;
import codesquad.security.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    private LoginAttemptService loginAttemptService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(SigninController.class);
    private static final String HTTP_REFERER_HEADER = "Referer";

    @GetMapping("/signin")
    public String getLoginPage(HttpServletRequest req) {
        req.getSession().setAttribute("prevPage", parseUrlFromRequest(req));
        return "login.html";
    }

    @GetMapping("/captcha")
    public String getCaptchaPage() {
        return "captchaview.html";
    }

    @PostMapping("/captcha")
    public String resetAttempt(HttpServletRequest req) {
        loginAttemptService.resetAttempt(req.getRemoteAddr());
        return "redirect:/signin";
    }

    @PostMapping("/signup")
    public String joinAccount(Account account, HttpServletRequest req) {
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        account.setRole("USER");
        accountRepository.save(account);
        return "redirect:" + parseUrlFromRequest(req);
    }

    private static String parseUrlFromRequest(HttpServletRequest req){
        return req.getHeader(HTTP_REFERER_HEADER);
    }

}
