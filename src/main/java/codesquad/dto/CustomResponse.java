package codesquad.dto;

import lombok.Data;

@Data
public class CustomResponse {
    private int status;
    private String message;
    private Object body;

    public CustomResponse() {
    }

    public CustomResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomResponse(int status, String message, Object body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }
}
