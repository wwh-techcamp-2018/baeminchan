package codesquad.security;

public class Error {
    private String fieldName;

    private String errorMessage;

    public Error() {
    }

    public Error(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "Error [fieldName=" + fieldName + ", errorMessage=" + errorMessage + "]";
    }
}
