package codesquad.exception;

public class UnAuthenticationException extends RuntimeException {
    public static final String NOT_MATCH_PASSWORD = "비밀번호가 일치하지 않습니다.";
    public static final String NOT_EXIST_EMAIL = "일치하는 이메일이 없습니다.";

    public UnAuthenticationException() {
    }

    public UnAuthenticationException(String message) {
        super(message);
    }

    public UnAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(Throwable cause) {
        super(cause);
    }

    public UnAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
