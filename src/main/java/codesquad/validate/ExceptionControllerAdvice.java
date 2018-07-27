package codesquad.validate;

import codesquad.support.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        log.debug("ErrorResponse CALL! + {}" , exception.toString());

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        ErrorResponse errorResponse = new ErrorResponse();
        for (ObjectError objectError : errors) {
            log.debug("object error : {}", objectError);
            FieldError fieldError = (FieldError) objectError;
            //log.debug("FieldError {}", fieldError);
            errorResponse.registErrorMessage(new ValidationError(fieldError.getDefaultMessage(), fieldError.getField()));
        }
        return errorResponse;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( {IllegalArgumentException.class, NotExistException.class} )
    public ErrorResponse handleIllegalArgumentException(RuntimeException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.registErrorMessage(new ValidationError(exception.getMessage()));
        return errorResponse;
    }
}
