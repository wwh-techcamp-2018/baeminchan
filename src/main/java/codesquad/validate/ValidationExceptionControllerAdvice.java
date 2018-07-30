package codesquad.validate;

import codesquad.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<ValidationErrorList> handleValidationException(MethodArgumentNotValidException exception) {
        ValidationErrorList validationErrorList = new ValidationErrorList();
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for (ObjectError error : errors) {
            validationErrorList.addValidationError(ValidationError.createValidationError(error));
        }
        return new CustomResponse<ValidationErrorList>(CustomResponse.MSG.VALIDATION_ERROR, validationErrorList);
    }
}
