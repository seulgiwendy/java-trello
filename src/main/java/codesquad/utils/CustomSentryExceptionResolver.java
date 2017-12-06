package codesquad.utils;

import io.sentry.Sentry;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomSentryExceptionResolver implements HandlerExceptionResolver, Ordered{

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Sentry.init("https://b4bfae902b734bc099651dfddd3b72b2:0f91f71192d14b68b889e30dc00487fe@sentry.io/247597");
        Sentry.capture(e);


        return null;
    }
}
