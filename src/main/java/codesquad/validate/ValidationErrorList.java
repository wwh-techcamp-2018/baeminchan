package codesquad.validate;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorList {
    private List<ValidationError> validationErrorList;

    public ValidationErrorList() {
        validationErrorList = new ArrayList<>();
    }

    public void addValidationError(ValidationError error) {
        validationErrorList.add(error);
    }
}
