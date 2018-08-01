package codesquad.security;

import codesquad.exception.UnAuthenticationException;
import codesquad.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor msa;


    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorsResponse unAuthorizedExcpetion(UnAuthorizedException exception) {
        ErrorsResponse response = new ErrorsResponse();
        response.addError(new Error("role", exception.getMessage()));
        return response;
    }

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorsResponse unAuthentication(UnAuthenticationException exception) {
        ErrorsResponse response = new ErrorsResponse();
        response.addError(new Error(exception.getFieldName(), exception.getMessage()));
        return response;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorsResponse entityNotFoundException(EntityNotFoundException exception) {
        ErrorsResponse response = new ErrorsResponse();
        // TODO : error handling..
//        response.addError(new Error("entity", exception.getMessage()));
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        ErrorsResponse response = new ErrorsResponse();
        for (ObjectError objectError : errors) {
            log.debug("object error : {}", objectError);
            FieldError fieldError = (FieldError) objectError;
            response.addError(new Error(fieldError.getField(), getErrorMessage(fieldError)));
        }
        return response;
    }

    private String getErrorMessage(FieldError fieldError) {
        return getFirstCode(fieldError)
                .map(code -> {
                    String errorMessage = msa.getMessage(code, fieldError.getArguments(), fieldError.getDefaultMessage());
                    log.info("error message: {}", errorMessage);
                    return errorMessage;
                }).orElseGet(null);
    }

    private Optional<String> getFirstCode(FieldError fieldError) {
        return Optional.ofNullable(fieldError.getCodes())
                .filter(codes -> codes.length > 0)
                .map(codes -> codes[0]);
    }
}
