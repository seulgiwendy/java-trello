package codesquad.security.Events;

import org.springframework.context.ApplicationEvent;

public class BruteForceDetectionEvent extends ApplicationEvent {

    public BruteForceDetectionEvent(Object source) {
        super(source);
    }


}
