package codesquad.exception;

public class UnAuthorityException extends RuntimeException {
    public UnAuthorityException() {
    }

    public UnAuthorityException(String message) {
        super(message);
    }
}
