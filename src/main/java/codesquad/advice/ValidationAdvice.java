package codesquad.advice;


import codesquad.domain.ValidationError;
import codesquad.domain.ValidationErrorResponse;
import codesquad.exception.NotFoundException;
import codesquad.exception.UnauthorizedException;

import codesquad.exception.UserVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class ValidationAdvice {

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handlerValidationException(MethodArgumentNotValidException exception){
        ValidationErrorResponse response = new ValidationErrorResponse();
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for(ObjectError objectError : errors){
            FieldError fieldError = (FieldError)objectError;
            response.addError(new ValidationError(fieldError.getField(), getErrorMessage(fieldError)));
        }
        return response;
    }

    private String getErrorMessage(FieldError fieldError) {
        Optional<String> code = getFirstCode(fieldError);
        if(!code.isPresent())
            return null;
        return messageSourceAccessor.getMessage(code.get(), fieldError.getArguments(), fieldError.getDefaultMessage());
    }

    private Optional<String> getFirstCode(FieldError fieldError){
        String[] codes = fieldError.getCodes();
        if(codes == null || codes.length == 0) {
            return Optional.empty();
        }
        return Optional.of(codes[0]);
    }

    @ExceptionHandler(UserVerificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse ununiqueException(UserVerificationException exception){
        return new ValidationErrorResponse().addError(exception.getError());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void unauthorizedException(UnauthorizedException exception) {

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void notFoundException(NotFoundException exception) {

    }

}
