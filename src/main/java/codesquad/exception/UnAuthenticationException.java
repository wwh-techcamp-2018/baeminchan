package codesquad.exception;

public class UnAuthenticationException extends RuntimeException {

    public UnAuthenticationException(String message) {
        super(message);
    }

    public static UnAuthenticationException invalidPassword() {
        return new UnAuthenticationException("비밀번호가 틀립니다.");
    }
}
