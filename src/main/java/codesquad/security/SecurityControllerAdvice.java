package codesquad.security;

import codesquad.exception.ErrorDetails;
import codesquad.exception.UnAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@ControllerAdvice
@RestController
public class SecurityControllerAdvice {

    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<ErrorDetails> unAuthentication(UnAuthenticationException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
