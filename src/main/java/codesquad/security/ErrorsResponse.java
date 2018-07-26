package codesquad.security;

import java.util.ArrayList;
import java.util.List;

public class ErrorsResponse {

    private boolean status;
    private List<Error> errors;

    public ErrorsResponse() {
        status = false;
        errors = new ArrayList<>();
    }

    public void addError(Error error) {
        errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ErrorsResponse [status=" + status + "]";
    }
}
