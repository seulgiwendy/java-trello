package codesquad.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;
import java.util.Map;

import static codesquad.configuration.SecurityConfig.DEFAULT_USER_ROLE;

public class OauthUserTokenService extends UserInfoTokenServices {


    public OauthUserTokenService(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
    }

    public OauthUserTokenService(ClientResources resources, SocialSigninProviders providers) {
        super(resources.getResource().getUserInfoUri(), resources.getClient().getClientId());
        setAuthoritiesExtractor(new OAUth2AuthoritiesExtractor(providers));

    }

    private class OAUth2AuthoritiesExtractor implements AuthoritiesExtractor {

        private String providerType;

        public OAUth2AuthoritiesExtractor(SocialSigninProviders providers) {
            this.providerType = providers.name();
        }

        @Override
        public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
            return AuthorityUtils.createAuthorityList(DEFAULT_USER_ROLE);
        }
    }
}
