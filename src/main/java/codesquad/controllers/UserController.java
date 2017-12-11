package codesquad.controllers;

import codesquad.dto.UserDto;
import codesquad.model.Account;
import codesquad.repository.AccountRepository;
import codesquad.security.Exceptions.NoRegisteredUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/api/user/join")
    public ResponseEntity<UserDto> createUser(Account account) {
        this.accountRepository.save(account);

        return new ResponseEntity<UserDto>(new UserDto(account), HttpStatus.OK);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<UserDto> loginCheck(Account account) {
        Account expected = this.accountRepository.findByUserId(account.getUserId()).orElseThrow(() -> new NoRegisteredUserException("fuck!"));
        if (expected == null) {
            return new ResponseEntity<UserDto>(new UserDto(new Account()), HttpStatus.FORBIDDEN);
        }
        if (expected.isLoginable(account)) {
            return new ResponseEntity<UserDto>(new UserDto(account), HttpStatus.OK);
        }
        return null;
    }



    @GetMapping("/loginForm")
    public String getLoginPage() {
        return "login.html";
    }
}
