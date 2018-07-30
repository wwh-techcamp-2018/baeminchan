package codesquad.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorResult {

    private List<Error> errors = new ArrayList<>();

    public ErrorResult add(Error error) {
        errors.add(error);
        return this;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
