package codesquad.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Authentication error");
    }

}
