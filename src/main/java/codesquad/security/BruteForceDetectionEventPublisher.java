package codesquad.security;

import codesquad.security.Events.BruteForceDetectionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BruteForceDetectionEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent() {
        BruteForceDetectionEvent event = new BruteForceDetectionEvent(this);
        applicationEventPublisher.publishEvent(event);
    }
}
