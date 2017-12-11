package codesquad.security.Exceptions;

public class NoRegisteredUserException extends RuntimeException {

    public NoRegisteredUserException(String message) {
        super(message);
    }
}
