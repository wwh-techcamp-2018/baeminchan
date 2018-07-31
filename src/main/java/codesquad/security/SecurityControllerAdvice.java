package codesquad.security;

import codesquad.error.ErrorResponse;
import codesquad.exception.NotMatchedException;
import codesquad.exception.UnAuthenticationException;
import codesquad.exception.UnAuthorityException;
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

    @ExceptionHandler(NotMatchedException.class)
    public ResponseEntity<ErrorResponse> notMatchedAccess(NotMatchedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UnAuthorityException.class)
    public ResponseEntity<ErrorResponse> UnAuthorityAccess(UnAuthorityException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }
}
