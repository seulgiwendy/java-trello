package codesquad.security.EventListener;

import codesquad.security.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class FormSigninFailListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{

    private static final Logger log = LoggerFactory.getLogger(FormSigninFailListener.class);

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        WebAuthenticationDetails details = (WebAuthenticationDetails)event.getAuthentication().getDetails();

        log.info("Signin fail event initiated from remote address : {}" , details.getRemoteAddress());
        loginAttemptService.loginFailed(details.getRemoteAddress());
    }
}
