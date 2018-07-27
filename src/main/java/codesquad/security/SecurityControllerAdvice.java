package codesquad.security;

import codesquad.exception.ErrorDetails;
import codesquad.exception.UnAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class SecurityControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<ErrorDetails> unAuthentication(UnAuthenticationException ex) {
        log.debug("UnAuthenticationException : " + ex.getClass() + ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.debug("MethodArgumentNotValidException : " + ex.getClass() + ex.getMessage());

        String errorMsg = "";
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errorMsg += error.getDefaultMessage();
        }

        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMsg);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
