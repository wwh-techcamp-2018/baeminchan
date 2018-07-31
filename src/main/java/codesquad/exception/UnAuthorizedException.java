package codesquad.exception;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException() {
        super("권한이 없습니다.");
    }

    public UnAuthorizedException(String message) { super(message); }

}
