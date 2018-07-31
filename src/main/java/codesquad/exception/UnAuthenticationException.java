package codesquad.exception;

public class UnAuthenticationException extends RuntimeException {
    public UnAuthenticationException() {
    }

    public UnAuthenticationException(String message) {
        super(message);
    }
}
