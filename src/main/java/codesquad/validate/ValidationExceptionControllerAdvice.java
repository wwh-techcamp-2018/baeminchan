package codesquad.validate;

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
public class ValidationExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ValidationExceptionControllerAdvice.class);

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor msa;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorsResponse handleValidationException(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        ValidationErrorsResponse response = new ValidationErrorsResponse();
        for (ObjectError error : errors) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                response.addValidationError(new ValidationError(fieldError.getField(), getErrorMessage(fieldError)));
                continue;
            }
            log.info("object error : {}", error.getDefaultMessage());
            response.addValidationError(new ValidationError(error.getObjectName(), msa.getMessage(error.getCode(), null, "입력값이 올바르지 않습니다.")));
        }
        return response;
    }

    private String getErrorMessage(FieldError fieldError) {
        Optional<String> code = getFirstCode(fieldError);
        if (!code.isPresent()) {
            return null;
        }

        String errorMessage = msa.getMessage(code.get(), fieldError.getArguments(), fieldError.getDefaultMessage());
        log.info("error message: {}", errorMessage);
        return errorMessage;
    }

    private Optional<String> getFirstCode(FieldError fieldError) {
        String[] codes = fieldError.getCodes();
        if (codes == null || codes.length == 0) {
            return Optional.empty();
        }
        return Optional.of(codes[0]);
    }
}