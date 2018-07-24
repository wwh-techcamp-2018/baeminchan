package codesquad.advice;

import codesquad.support.dto.ResponseModel;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class GlobalAdvice {

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor msa;

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel handleValidationError(MethodArgumentNotValidException exception) {
        List<ObjectError> rawErrors = exception.getBindingResult().getAllErrors();
        ResponseModel response = new ResponseModel();
        for (ObjectError rawError : rawErrors) {
            ResponseModel.Error error;
            if (rawError instanceof FieldError) {
                FieldError fieldError = (FieldError) rawError;
                error = new ResponseModel.Error(fieldError.getField(), getErrorMessage(fieldError));
            } else {
                error = new ResponseModel.Error(null, rawError.getDefaultMessage());
            }
            response.addError(error);
        }

        return response;
    }

    private String getErrorMessage(FieldError fieldError) {
        Optional<String> code = getFirstCode(fieldError);
        if (!code.isPresent()) {
            return null;
        }

        return msa.getMessage(code.get(), fieldError.getArguments(), fieldError.getDefaultMessage());
    }

    private Optional<String> getFirstCode(FieldError fieldError) {
        String[] codes = fieldError.getCodes();
        if (codes == null || codes.length == 0) {
            return Optional.empty();
        }
        return Optional.of(codes[0]);
    }
}
