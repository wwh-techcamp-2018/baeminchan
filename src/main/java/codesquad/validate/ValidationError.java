package codesquad.validate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;

@Slf4j
@Getter
public class ValidationError {
    private String fieldName;
    private String errorMessage;

    private ValidationError(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public static ValidationError createValidationError(ObjectError error) {
        String fieldName = error.getCodes()[1].split("\\.")[1];
        return new ValidationError(fieldName, error.getDefaultMessage());
    }
}
