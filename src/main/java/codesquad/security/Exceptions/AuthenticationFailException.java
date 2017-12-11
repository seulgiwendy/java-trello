package codesquad.security.Exceptions;

public class AuthenticationFailException extends RuntimeException{

    private static final String STD_AUTHFAIL_MSG = "AUTHENTICATION FAILED!";

    public AuthenticationFailException() {
        super(STD_AUTHFAIL_MSG);
    }
}
