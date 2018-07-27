package codesquad.validate;

import codesquad.exception.ExistedEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ValidationExceptionControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ObjectError>> joinFormNotValid(MethodArgumentNotValidException exception) {
        exception.getBindingResult().getAllErrors()
                .stream().forEach(e -> log.debug("[MethodArgumentNotValidException] {}", e.getDefaultMessage()));

        return new ResponseEntity<>(exception.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistedEmailException.class)
    public ResponseEntity<List<ObjectError>> existedEmailValid(ExistedEmailException exception) {
        log.debug("[ExistedEmailException] {}", exception.getMessage());
        ObjectError objectError = new ObjectError("existedEmailError", exception.getMessage());
        return new ResponseEntity<>(Collections.singletonList(objectError), HttpStatus.BAD_REQUEST);
    }
}
