package codesquad.validation;

import java.util.ArrayList;
import java.util.List;

public class ErrorResult {

    private List<Error> errors = new ArrayList<>();

    public ErrorResult() {

    }

    public void add(Error error) {
        errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
