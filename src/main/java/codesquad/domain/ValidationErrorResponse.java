package codesquad.domain;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<ValidationError> errors;

    public ValidationErrorResponse() {
        this.errors = new ArrayList<>();
    }

    public ValidationErrorResponse addError(ValidationError error) {
        this.errors.add(error);
        return this;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ValidationErrorResponse{" +
                "errors=" + errors +
                '}';
    }
}
