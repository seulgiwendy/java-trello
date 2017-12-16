package codesquad.model;

import codesquad.model.social.CustomUserProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SocialAccount implements UserDetails {

    private static final String DEFAULT_SOCIALUSER_PWD = "social";
    private CustomUserProperty property;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return DEFAULT_SOCIALUSER_PWD;
    }

    @Override
    public String getUsername() {
        return this.property.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
