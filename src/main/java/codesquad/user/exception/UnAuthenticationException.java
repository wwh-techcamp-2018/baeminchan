package codesquad.user.exception;

public class UnAuthenticationException extends RuntimeException {
    public UnAuthenticationException(String message) {
        super(message);
    }
}
