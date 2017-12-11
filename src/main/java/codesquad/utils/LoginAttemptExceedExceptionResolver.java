package codesquad.utils;


import codesquad.security.Exceptions.LoginAttemptExceedException;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginAttemptExceedExceptionResolver implements HandlerExceptionResolver, Ordered{

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof LoginAttemptExceedException) {
            return new ModelAndView("redirect:/");
        }
        return null;
    }
}
