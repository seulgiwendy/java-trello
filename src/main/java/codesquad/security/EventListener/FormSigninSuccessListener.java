package codesquad.security.EventListener;

import codesquad.security.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class FormSigninSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if (!(event.getAuthentication().getDetails() instanceof WebAuthenticationDetails)) {
            return;
        }
        WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuthentication().getDetails();

        loginAttemptService.loginSucceeded(details.getRemoteAddress());
    }
}
