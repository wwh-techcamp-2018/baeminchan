package codesquad.support.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
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
    private List<ResponseError> errors;

    public static <T> ResponseModel<T> ofSuccess(T data, String message) {
        return new ResponseModel<>(data, message);
    }

    public static ResponseModel ofErrors(ResponseError... errors) {
        ResponseModel responseModel = new ResponseModel();
        for (ResponseError error : errors) {
            responseModel.addError(error);
        }
        return responseModel;
    }

    public ResponseModel() {
        this.errors = new ArrayList<>();
    }

    public ResponseModel(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public void addError(ResponseError error) {
        errors.add(error);
    }
}
