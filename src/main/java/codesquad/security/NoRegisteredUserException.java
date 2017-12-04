package codesquad.security;

public class NoRegisteredUserException extends RuntimeException {

    public NoRegisteredUserException(String message) {
        super(message);
    }
}
