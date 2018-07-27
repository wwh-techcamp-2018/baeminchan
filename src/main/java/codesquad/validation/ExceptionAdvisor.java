package codesquad.validation;

import codesquad.BadRequestException;
import codesquad.UnAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
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
public class ExceptionAdvisor {
    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvisor.class);

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleBadRequest(BadRequestException exception) {
        log.debug("handleBadRequest is called");
        ErrorResult errorResult = new ErrorResult();
        errorResult.add(exception.of());

        return errorResult;
    }

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleUnAuthenticationException(UnAuthenticationException exception) {
        log.debug("handleBadRequest is called");
        ErrorResult errorResult = new ErrorResult();
        errorResult.add(exception.of());

        return errorResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleValidationException(MethodArgumentNotValidException exception) {
        log.debug("handleValidationException is called");
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        ErrorResult errorResult = new ErrorResult();
        for (ObjectError objectError : errors) {
            FieldError fieldError = (FieldError) objectError;
            log.debug("field: {}, message: {}", fieldError.getField(), getErrorMessage(fieldError));
            errorResult.add(new Error(fieldError.getField(), getErrorMessage(fieldError)));
        }

        return errorResult;
    }

    private String getErrorMessage(FieldError fieldError) {
        Optional<String> code = getFirstCode(fieldError);
        if(!code.isPresent())
            return null;
        String errorMessage = messageSourceAccessor.getMessage(code.get(), fieldError.getArguments(), fieldError.getDefaultMessage());
        log.debug("error message: {}", errorMessage);
        return errorMessage;
    }

    private Optional<String> getFirstCode(FieldError fieldError) {
        String[] codes = fieldError.getCodes();
        log.debug("codes: {}", codes);
        if(codes.length == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(codes[0]);
    }
}
