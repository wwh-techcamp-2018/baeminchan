package codesquad.exception;

public class NotMatchedException extends RuntimeException {
    public NotMatchedException() {
        super();
    }

    public NotMatchedException(String message) {
        super(message);
    }
}
