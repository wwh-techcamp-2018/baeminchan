package codesquad.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;

    private ErrorResponse() {}
    private ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse ofString(String message) {
        return new ErrorResponse(message);
    }


}

