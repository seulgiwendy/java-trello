package codesquad.controllers;

import codesquad.dto.UserDto;
import codesquad.model.User;
import codesquad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/user")
    public ResponseEntity<UserDto> createUser(User user) {
        this.userRepository.save(user);

        return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<UserDto> loginCheck(User user) {
        User expected = this.userRepository.findByUserId(user.getUserId());
        if (expected == null) {
            return new ResponseEntity<UserDto>(new UserDto(new User()), HttpStatus.FORBIDDEN);
        }
        if (expected.isLoginable(user)) {
            return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/loginForm")
    public String getLoginPage() {
        return "login.html";
    }
}
