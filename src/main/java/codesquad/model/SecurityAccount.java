package codesquad.model;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityAccount extends User{

    private static final String ROLE_PREFIX = "ROLE_";
    private Account account;

    public SecurityAccount(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityAccount(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public SecurityAccount(Account account) {
        super(account.getUserId(), account.getPassword(), true, true, true, true, makeAuthorities(account.getRole()));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    private static Collection<? extends GrantedAuthority> makeAuthorities(List<Role> role){
        return role.stream().map(r -> new SimpleGrantedAuthority(r.getRole().name())).collect(Collectors.toList());
    }
}
