package codesquad.exception;

public class UnAuthenticationException extends RuntimeException {

    public UnAuthenticationException(String message) {
        super(message);
    }

    public static UnAuthenticationException invalidEmail() {
        return new UnAuthenticationException("이메일이 틀립니다.");
    }

    public static UnAuthenticationException invalidPassword() {
        return new UnAuthenticationException("비밀번호가 틀립니다.");
    }
}
