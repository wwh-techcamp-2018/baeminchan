package codesquad;

import codesquad.validation.Error;

public class UnAuthenticationException extends RuntimeException {
    private String fieldName;

    public UnAuthenticationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public Error of() {
        return new Error(fieldName, getMessage());
    }
}