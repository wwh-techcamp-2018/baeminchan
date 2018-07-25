package codesquad.advice;

import codesquad.exception.ConflictException;
import codesquad.exception.UnauthenticatedException;
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
                error = ResponseModel.Error.of(fieldError.getField(), getErrorMessage(fieldError));
            } else {
                error = ResponseModel.Error.of(null, rawError.getDefaultMessage());
            }
            response.addError(error);
        }

        return response;
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

    @ExceptionHandler({ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseModel handleConflictException(ConflictException e) {
        return ResponseModel.ofErrors(ResponseModel.Error.of(e.getFieldName(), e.getMessage()));
    }

    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseModel handleUnauthenticatedException(UnauthenticatedException e) {
        return ResponseModel.ofErrors(ResponseModel.Error.of(null, e.getMessage()));
    }
}
