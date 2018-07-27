package codesquad.support;

import java.util.Objects;

public class ValidationError {
    private String message;
    private String field;

    public ValidationError(){

    }

    public ValidationError(String message) {
        this.message = message;
    }

    public ValidationError(String message, String field){
        this(message);
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationError error = (ValidationError) o;
        return Objects.equals(message, error.message) &&
                Objects.equals(field, error.field);
    }

    @Override
    public int hashCode() {

        return Objects.hash(message, field);
    }
}
