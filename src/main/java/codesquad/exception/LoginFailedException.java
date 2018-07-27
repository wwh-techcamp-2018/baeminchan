package codesquad.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String msg) {
        super(msg);
    }
}
