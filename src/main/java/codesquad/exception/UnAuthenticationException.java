package codesquad.exception;

public class UnAuthenticationException extends Exception {

    private static final long serialVersionUID = 1L;
    private String fieldName;

    public UnAuthenticationException() {
        super();
    }

    public UnAuthenticationException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(String message) {
        super(message);
    }

    public UnAuthenticationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public UnAuthenticationException(Throwable cause) {
        super(cause);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
