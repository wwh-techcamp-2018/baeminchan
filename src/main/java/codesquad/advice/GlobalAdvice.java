package codesquad.advice;

import codesquad.exception.*;
import codesquad.support.dto.response.ResponseError;
import codesquad.support.dto.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel handleValidationError(MethodArgumentNotValidException exception) {
        ResponseModel response = new ResponseModel();
        List<ObjectError> rawErrors = exception.getBindingResult().getAllErrors();

        rawErrors.forEach(e -> response.addError(new ResponseError(e)));
        return response;
    }

    @ExceptionHandler({ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseModel handleConflictException(ConflictException e) {
        return ResponseModel.ofErrors(ResponseError.of(e.getFieldName(), e.getMessage()));
    }

    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseModel handleUnauthenticatedException(UnauthenticatedException e) {
        return ResponseModel.ofErrors(ResponseError.of(null, e.getMessage()));
    }


    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseModel handleForbiddenException(ForbiddenException e) {
        return ResponseModel.ofErrors(ResponseError.of(null, e.getMessage()));
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel handleBadRequestException(BadRequestException e) {
        return ResponseModel.ofErrors(ResponseError.of(e.getFieldName(), e.getMessage()));
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseModel handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseModel.ofErrors(ResponseError.of(e.getFieldName(), e.getMessage()));
    }
}
