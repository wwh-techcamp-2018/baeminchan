package codesquad.security;

import codesquad.exception.ErrorResponse;
import codesquad.exception.NotMatchException;
import codesquad.exception.UnAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<ErrorResponse> unAuthentication(Exception exception) {
        log.debug("UnAuthenticationException is happened!");
        return new ResponseEntity<ErrorResponse>(ErrorResponse.ofString(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotMatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> notMatch(Exception exception) {
        log.debug("NotMatchException is happened!");
        return new ResponseEntity<ErrorResponse>(ErrorResponse.ofString(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
