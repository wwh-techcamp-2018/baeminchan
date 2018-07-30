package codesquad.security;

import codesquad.exception.CannotDeleteException;
import codesquad.exception.UnAuthenticationException;
import codesquad.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class SecurityControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void emptyResultData() {
        log.debug("EntityNotFoundException is happened!");
    }

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void unAuthorized() {
        log.debug("UnAuthorizedException is happened!");
    }

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void unAuthentication() {
        log.debug("UnAuthenticationException is happened!");
    }

    @ExceptionHandler(CannotDeleteException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void cannotDelete(CannotDeleteException e) {
        log.debug(e.getMessage());
    }
}
