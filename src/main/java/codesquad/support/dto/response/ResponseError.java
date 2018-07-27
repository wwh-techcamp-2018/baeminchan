package codesquad.support.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.annotation.Resource;
import java.util.Optional;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class ResponseError {
    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor msa;

    private String field;
    private String message;

    private ResponseError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ResponseError(ObjectError rawError) {
        if (rawError instanceof FieldError) {
            FieldError fieldError = (FieldError) rawError;
            this.field = fieldError.getField();
            this.message = getErrorMessage(fieldError);
            return;
        }
        this.field = null;
        this.message = rawError.getDefaultMessage();
    }

    public static ResponseError of(String field, String message) {
        return new ResponseError(field, message);
    }

    private String getErrorMessage(FieldError fieldError) {
        return getFirstCode(fieldError)
                .map(s -> msa.getMessage(s, fieldError.getArguments(), fieldError.getDefaultMessage()))
                .orElse(null);
    }

    private Optional<String> getFirstCode(FieldError fieldError) {
        String[] codes = fieldError.getCodes();
        if (codes == null || codes.length == 0) {
            return Optional.empty();
        }
        return Optional.of(codes[0]);
    }
}