package codesquad.security.Exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginAttemptExceedException extends RuntimeException {

    public LoginAttemptExceedException(String msg) {
        super(msg);
    }

}
