package codesquad.support;

public class CustomFieldError {
    private String field;
    private String message;

    public CustomFieldError(){
    }

    public CustomFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CustomFieldError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}