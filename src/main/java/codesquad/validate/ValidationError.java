package codesquad.validate;

import lombok.Getter;

@Getter
public class ValidationError {
    private String fieldName;

    private String errorMessage;

    public ValidationError(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ValidationError [fieldName=" + fieldName + ", errorMessage=" + errorMessage + "]";
    }
}