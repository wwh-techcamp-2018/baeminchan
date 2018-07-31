package codesquad.user;

import codesquad.RestResponse;
import codesquad.user.exception.UnAuthenticationException;
import codesquad.user.exception.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public RestResponse<?> unAuthentication(UnAuthenticationException e) {
        return RestResponse.error(e.getMessage()).build();
    }


    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public RestResponse<?> unAuthorization(UnAuthorizedException e) {
        return RestResponse.error(e.getMessage()).build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestResponse<?> entityNotFound(EntityNotFoundException e) {
        return RestResponse.error(e.getMessage()).build();
    }
}
