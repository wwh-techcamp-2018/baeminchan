package codesquad.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private String fieldName;

    public BadRequestException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
