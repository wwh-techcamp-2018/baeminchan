package codesquad.exception;

public class ExistedEmailException extends RuntimeException {
    public ExistedEmailException() {
        super();
    }

    public ExistedEmailException(String msg) {
        super(msg);
    }
}
