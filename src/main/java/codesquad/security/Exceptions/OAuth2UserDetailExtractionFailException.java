package codesquad.security.Exceptions;

import org.springframework.security.core.AuthenticationException;


public class OAuth2UserDetailExtractionFailException extends AuthenticationException {

    public OAuth2UserDetailExtractionFailException(String msg, Throwable t) {
        super(msg, t);
    }
}
