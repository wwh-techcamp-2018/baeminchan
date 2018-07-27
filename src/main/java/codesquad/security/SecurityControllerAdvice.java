package codesquad.security;

import codesquad.error.ErrorResponse;
import codesquad.exception.UnAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SecurityControllerAdvice {
    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<ErrorResponse> unAuthenticatedAccess(UnAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
    }
}
