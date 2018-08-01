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
        OK("success"),
        VALIDATION_ERROR("valid_error"), LOGIN_FAILED_ERROR("login_failed"), ALREADT_EXISTS_USER_ERROR("already_exists_user"),
        AUTHENTICATION_ERROR("권한이 없습니다."),CATEGORY_NOT_FOUND_ERROR("카테고리가 없습니다.");


        final private String msg;

        private MSG(String name) {
            this.msg = name;
        }

        public String getMsg() {
            return msg;
        }
    }

}