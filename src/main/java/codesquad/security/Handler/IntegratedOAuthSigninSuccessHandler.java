package codesquad.security.Handler;

import codesquad.repository.AccountRepository;
import codesquad.security.SocialSigninProviders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class IntegratedOAuthSigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private AccountRepository accountRepository;

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
    }
}
