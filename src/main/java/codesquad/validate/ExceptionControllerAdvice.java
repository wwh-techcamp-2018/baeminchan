package codesquad.validate;

import codesquad.support.CustomFieldError;
import codesquad.support.ErrorResponse;
import codesquad.support.JsonResponse;
import codesquad.support.NotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

//    @Resource(name = "messageSourceAccessor")
//    private MessageSourceAccessor msa;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        log.debug("ErrorResponse CALL! + {}" , exception.toString());

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        ErrorResponse errorResponse = new ErrorResponse();
        for (ObjectError objectError : errors) {
            log.debug("object error : {}", objectError);
            FieldError fieldError = (FieldError) objectError;
            log.debug("field error : {}", fieldError);
            log.debug("contents : {} {}", fieldError.getField(), fieldError.getDefaultMessage());
            errorResponse.registErrorMessage(new CustomFieldError(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        return errorResponse;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( {IllegalArgumentException.class, NotExistException.class} )
    public ErrorResponse handleIllegalArgumentException(RuntimeException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.registErrorMessage(new CustomFieldError(null, exception.getMessage()));
        return errorResponse;
    }
}
