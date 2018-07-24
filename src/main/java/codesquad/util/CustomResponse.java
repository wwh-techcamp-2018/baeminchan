package codesquad.util;

import codesquad.validate.ValidationErrorList;
import lombok.Getter;

@Getter
public class CustomResponse<T> {

    public enum ERROR_MSG {
        VALIDATION_ERROR("valid_error");

        final private String msg;

        private ERROR_MSG(String name) {
            this.msg = name;
        }

        public String getMsg() {
            return msg;
        }
    }

    private String message;
    T data;

    public CustomResponse(ERROR_MSG message, T data) {
        this.message = message.getMsg();
        this.data = data;
    }

}