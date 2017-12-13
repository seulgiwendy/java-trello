package codesquad.security.Handler;

import codesquad.security.Exceptions.LoginAttemptExceedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class IntegratedFormloginFailureHandler implements AuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(IntegratedFormloginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        if (isBruteForceEvent(e)) {
            log.error("brute force event detected from failure handler!");
            log.error("cause of exception : {}" , e.getCause().getClass().getName());
            res.sendRedirect("/captcha");
            return;
        }
        log.error("general sign-in fail event detected from failure handler!");
        res.sendRedirect("/signin");
    }

    private boolean isBruteForceEvent(AuthenticationException e) {
        return e.getCause() instanceof LoginAttemptExceedException;
    }
}
