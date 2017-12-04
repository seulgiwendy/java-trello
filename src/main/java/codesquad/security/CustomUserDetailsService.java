package codesquad.security;

import codesquad.model.Account;
import codesquad.model.Role;
import codesquad.model.SecurityAccount;
import codesquad.repository.AccountRepository;
import com.google.common.collect.Lists;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("load username : {}" , username);

        return accountRepository.findByUserId(username).filter(a -> a != null)
                .map(a -> new SecurityAccount(a)).orElseThrow(() -> new UsernameNotFoundException("No User Available!"));
        
    }

    public void saveSocialUser(Map<String, Object> userinfo) {
       Account account = new Account();
       account.setUserId((String)userinfo.get("login"));
       account.setName((String)userinfo.get("name"));
       account.addRole(new Role("USER"));
       account.setPassword("1111");

       accountRepository.save(account);
    }

    private User buildUserForAuthentication(Account account, List<GrantedAuthority> authorities) {
        return new User(account.getUserId(), account.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority() {
        List<GrantedAuthority> authorities = Lists.newArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
        
    }


}
