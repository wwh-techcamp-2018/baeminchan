package codesquad.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UnAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String fieldName;

    public UnAuthenticationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
