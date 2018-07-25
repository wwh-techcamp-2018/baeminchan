package codesquad.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsResponse {

    private boolean status;
    private List<ValidationError> errors;

    public ValidationErrorsResponse() {
        status = false;
        errors = new ArrayList<>();
    }

    public void addValidationError(ValidationError error) {
        errors.add(error);
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ValidationErrorsResponse [status=" + status + "]";
    }
}
