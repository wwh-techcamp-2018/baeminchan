package codesquad.support;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    List<ValidationError> error;

    public ErrorResponse() {
        this.error = new ArrayList<>();
    }

    public List<ValidationError> getError() {
        return error;
    }

    public void setError(List<ValidationError> error) {
        this.error = error;
    }

    public void registErrorMessage(ValidationError validationError){
        error.add(validationError);
    }

}
