package codesquad;

import codesquad.validation.Error;

public class BadRequestException extends RuntimeException {

    private String fieldName;

    public BadRequestException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public Error of() {
        return new Error(fieldName, getMessage());
    }
}
