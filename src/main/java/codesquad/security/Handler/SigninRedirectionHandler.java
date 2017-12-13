package codesquad.security.Handler;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninRedirectionHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public SigninRedirectionHandler(String defaultUrl) {
        setDefaultTargetUrl(defaultUrl);
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
