package codesquad.exception;

public class UnAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String fieldName;

    public UnAuthorizedException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
