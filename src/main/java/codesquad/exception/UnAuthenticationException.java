package codesquad.exception;

import lombok.Getter;

@Getter
public class UnAuthenticationException extends Exception {

    private static final long serialVersionUID = 1L;
    private String fieldName;

    public UnAuthenticationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
