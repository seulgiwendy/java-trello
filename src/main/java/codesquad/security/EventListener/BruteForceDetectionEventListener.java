package codesquad.security.EventListener;

import codesquad.security.Events.BruteForceDetectionEvent;
import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@Component
public class BruteForceDetectionEventListener implements ApplicationListener<BruteForceDetectionEvent> {

    private static final Logger log = LoggerFactory.getLogger(BruteForceDetectionEventListener.class);

    @Override
    public void onApplicationEvent(BruteForceDetectionEvent bruteForceDetectionEvent) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attributes == null) {
            log.error("shit! no response instance injected! fuck!");
        }


        try {
            if (attributes.getResponse() == null) {
                log.error("fuck! no response injected! shit!");
                return;
            }
            attributes.getResponse().sendRedirect("/captcha");
        } catch (IOException e) {
            Sentry.capture(e);
        }
    }
}
