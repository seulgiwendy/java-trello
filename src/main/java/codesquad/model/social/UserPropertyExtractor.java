package codesquad.model.social;

import java.util.Map;

@FunctionalInterface
public interface UserPropertyExtractor {

    public CustomUserProperty extractSocialProperties(Map<String, Object> map) ;
}
