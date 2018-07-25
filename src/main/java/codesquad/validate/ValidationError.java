package codesquad.validate;

import lombok.Getter;
import org.springframework.validation.ObjectError;

@Getter
public class ValidationError {
    private String fieldName;
    private String errorMessage;

    private ValidationError(String fieldName,String errorMessage){
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public static ValidationError createValidationError(ObjectError error){
        return new ValidationError(error.getObjectName(),error.getDefaultMessage());
    }
}
