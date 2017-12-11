package codesquad.security.EventListener;

import codesquad.security.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Component
public class AuthenticationEventListener {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);
    private final PrincipalExtractor extractor = new FixedPrincipalExtractor();

    @Resource(name = "customUserDetailsService")
    private CustomUserDetailsService customUserDetailsService;

    @EventListener
    public void handleAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) throws IOException {
        if (!(event.getAuthentication() instanceof OAuth2Authentication)) {
            log.error("Fuck! You've caught a wrong event! Shit!");
            return;
        }

        OAuth2Authentication authentication = (OAuth2Authentication)event.getAuthentication();
        authentication.getPrincipal();

        Map<String, Object> userinfo = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        log.debug("User's details are as follows : {}" , userinfo.toString());
        System.out.println(String.format("user's details are as follows : %s", userinfo.toString()));

        UserDetails userDetails = getUser(userinfo);
    }

    private UserDetails getUser(Map<String, Object> userinfo) {
        String username = this.extractor.extractPrincipal(userinfo).toString();

        log.debug("start extracting OAuth user details of user name : {}" , username);

        UserDetails user = null;

        try {
            user = this.customUserDetailsService.loadUserByUsername(username);
        } catch(UsernameNotFoundException e) {
            log.debug("create new social user, username : {}" , username);
            this.customUserDetailsService.saveSocialUser(userinfo);
        }

        return user;
    }
}
