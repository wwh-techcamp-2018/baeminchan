package codesquad.exception;

import codesquad.domain.DomainField;
import codesquad.validation.Error;
import org.springframework.http.HttpStatus;

public abstract class AbstractBaseException extends RuntimeException {
    private DomainField domainField;

    AbstractBaseException(DomainField domainField, String message){
        super(message);
        this.domainField = domainField;
    }

    public abstract HttpStatus getStatus();

    public Error of() {
        return new Error(domainField.getFieldName(), getMessage());
    }
}
