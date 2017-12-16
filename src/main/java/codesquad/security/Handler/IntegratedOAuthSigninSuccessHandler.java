package codesquad.security.Handler;

import codesquad.repository.AccountRepository;
import codesquad.security.CustomUserDetailsService;
import codesquad.security.SocialSigninProviders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class IntegratedOAuthSigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(IntegratedOAuthSigninSuccessHandler.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private SocialSigninProviders provider;


    @Deprecated
    public IntegratedOAuthSigninSuccessHandler(String defaultUrl) {
        setDefaultTargetUrl(defaultUrl);
    }

    public IntegratedOAuthSigninSuccessHandler(String defaultUrl, SocialSigninProviders provider) {
        setDefaultTargetUrl(defaultUrl);
        this.provider = provider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws ServletException, IOException{
        HttpSession session = req.getSession();

        if (session != null) {
            String redirectTarget = (String)session.getAttribute("prevPage");
            if (redirectTarget != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(req, res, redirectTarget);
            } else {
                super.onAuthenticationSuccess(req, res, auth);
            }
        } else {
            super.onAuthenticationSuccess(req, res, auth);
        }

        if (auth.getName() != null) log.debug("authentication bearer's name is : {}, class type is : {}", auth.getName(), auth.getClass().getName());
        OAuth2Authentication authentication = (OAuth2Authentication)auth;

        log.debug("authentication details are : {}" , auth.getDetails().toString());
        log.debug("user's details are : {}", authentication.getUserAuthentication().getDetails());

    }
}
