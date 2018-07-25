package codesquad.exception;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(){
        super("login failed");
    }
}
