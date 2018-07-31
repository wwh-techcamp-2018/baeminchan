package codesquad.error;

import codesquad.validate.RestStatus;

public class ErrorResponse extends RestStatus {
    private String message;

    public ErrorResponse() {
        super(false);
    }

    public ErrorResponse(String message) {
        super(false);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

