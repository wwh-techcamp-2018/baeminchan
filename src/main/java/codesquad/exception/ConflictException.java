package codesquad.exception;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {

    private String fieldName;

    public ConflictException(String fieldName, String message) {
        super(message + "이(가) 중복되었습니다.");
        this.fieldName = fieldName;
    }
}
