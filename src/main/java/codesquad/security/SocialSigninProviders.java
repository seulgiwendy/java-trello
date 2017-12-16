package codesquad.security;

import codesquad.model.social.UserPropertyExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

public enum SocialSigninProviders {

    GITHUB(new FixedPrincipalExtractor()),

    NAVER(map -> map.get("response"));

    private final String ROLE_PREFIX = "ROLE_";

    private PrincipalExtractor extractor;
    private UserPropertyExtractor propertyExtractor;

    SocialSigninProviders(PrincipalExtractor extractor) {
        this.extractor = extractor;
    }

    public PrincipalExtractor getExtractor() {
        return extractor;
    }

    public String getUserPrimaryName(Map<String, Object> userinfo) {
        return (String)this.extractor.extractPrincipal(userinfo);
    }
}


