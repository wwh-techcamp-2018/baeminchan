package codesquad.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private String fieldName;

    public ResourceNotFoundException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
