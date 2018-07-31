package codesquad.support.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResponseModel<T> {
    private T data;
    private String message;
    private List<ResponseModel.Error> errors;

    public static <T> ResponseModel<T> ofSuccess(T data, String message) {
        return new ResponseModel<T>(data, message);
    }

    public static ResponseModel ofErrors(Error... errors) {
        ResponseModel responseModel = new ResponseModel();
        for (Error error : errors) {
            responseModel.addError(error);
        }
        return responseModel;
    }

    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @NoArgsConstructor
    public static class Error {
        private String field;
        private String message;

        private Error(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public static Error of(String field, String message) {
            return new Error(field, message);
        }
    }

    public ResponseModel() {
        this.errors = new ArrayList<>();
    }

    public ResponseModel(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public void addError(Error error) {
        errors.add(error);
    }
}
