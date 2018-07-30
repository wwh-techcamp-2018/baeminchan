package codesquad.util;

import lombok.Getter;

@Getter
public class CustomResponse<T> {

    T data;
    private String message;
    public CustomResponse(MSG message, T data) {
        this.message = message.getMsg();
        this.data = data;
    }

    public enum MSG {
        VALIDATION_ERROR("valid_error"), LOGIN_FAILED_ERROR("login_failed"), ALREADT_EXISTS_USER_ERROR("already_exists_user"),
        OK("success");


        final private String msg;

        private MSG(String name) {
            this.msg = name;
        }

        public String getMsg() {
            return msg;
        }
    }

}