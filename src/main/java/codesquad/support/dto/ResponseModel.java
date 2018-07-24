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

    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @NoArgsConstructor
    public static class Error {
        private String field;
        private String message;

        public Error(String field, String message) {
            this.field = field;
            this.message = message;
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
