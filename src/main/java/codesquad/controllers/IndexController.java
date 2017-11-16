package codesquad.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

    @GetMapping("/fuckyou")
    public String returnHello() {
        return "fuck you world!";
    }

}
