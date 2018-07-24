package codesquad.validation;

import java.util.Objects;

public class Error {
    private String fieldName;
    private String errorMessage;

    public Error () {

    }

    public Error(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return Objects.equals(fieldName, error.fieldName) &&
                Objects.equals(errorMessage, error.errorMessage);
    }

    @Override
    public String toString() {
        return "Error{" +
                "fieldName='" + fieldName + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
