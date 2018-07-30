package codesquad.exception;

public class AlreadyExistsUserException extends RuntimeException {
    public AlreadyExistsUserException() {
        super("user already exists");
    }
}
