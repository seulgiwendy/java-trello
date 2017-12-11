package codesquad.utils;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFailExceptionResolver extends DefaultHandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(AuthFailExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        log.debug("caught exception's type is : {}" , e.getClass().getName());

        StringBuilder exceptionDetails = new StringBuilder();

        ModelAndView mav = new ModelAndView("error");
//        Arrays.stream(e.getStackTrace()).map(t -> t.toString()).forEach(s -> {
////            exceptionDetails.append(s);
////            exceptionDetails.append(System.getProperty("line.separator"));
////        });


        mav.addObject("error", e.getMessage() + "\n" + Throwables.getRootCause(e).getStackTrace().toString());
        return mav;
    }
}
