package codesquad.support;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    List<CustomFieldError> errors;

    public ErrorResponse(){
        errors = new ArrayList<>();
    }

    public List<CustomFieldError> getError() {
        return errors;
    }

    public void setError(List<CustomFieldError> error) {
        this.errors = error;
    }

    public void registErrorMessage(CustomFieldError message){
        errors.add(message);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errors=" + errors +
                '}';
    }
}
