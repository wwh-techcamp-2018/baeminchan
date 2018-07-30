package codesquad.exception;

public class NotMatchedException extends RuntimeException {
    public NotMatchedException() {
    }

    public NotMatchedException(String message) {
        super(message);
    }
}
