package codesquad.exception;

public class ValidationException extends RuntimeException {
    public ValidationException() {
        super();
    }

    public ValidationException(String msg) {
        super(msg);
    }
}
