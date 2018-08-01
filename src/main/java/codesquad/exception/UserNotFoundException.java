package codesquad.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("NormalUser Not Found Exception");
    }
}
