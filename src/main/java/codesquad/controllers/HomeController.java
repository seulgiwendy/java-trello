package codesquad.controllers;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static SentryClient sentryClient;

    @GetMapping("/")
    public String getHomePage() {
        return "index.html";
    }
}
