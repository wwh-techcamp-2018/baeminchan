package codesquad.exception;

import codesquad.domain.DomainField;
import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractBaseException {

    public BadRequestException(DomainField field, String message) {
        super(field, message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
