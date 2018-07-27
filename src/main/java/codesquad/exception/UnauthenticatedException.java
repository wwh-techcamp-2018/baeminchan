package codesquad.exception;

public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException() {
        super("이메일 또는 비밀번호가 일치하지 않습니다.");
    }
}
