package codesquad.user;

import codesquad.RestResponse;
import codesquad.user.exception.UnAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestResponse<?> unAuthentication(UnAuthenticationException e) {
        return RestResponse.error(e.getMessage()).build();
    }
}
