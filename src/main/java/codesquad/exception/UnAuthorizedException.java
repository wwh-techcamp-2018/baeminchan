package codesquad.exception;

import codesquad.domain.DomainField;
import codesquad.validation.Error;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends AbstractBaseException {

    public UnAuthorizedException(DomainField field, String message) {
        super(field, message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}