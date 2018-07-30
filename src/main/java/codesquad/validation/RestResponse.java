package codesquad.validation;

import java.util.ArrayList;
import java.util.List;

public class RestResponse<T> {

    private T data;

    private List<Error> errors = new ArrayList<>();

    public RestResponse() {

    }

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse add(Error error) {
        errors.add(error);
        return this;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }
}
