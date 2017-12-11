package codesquad.security.Exceptions;

public class LoginAttemptExceedException extends RuntimeException {

    public LoginAttemptExceedException(String msg) {
        super(msg);
    }

}
