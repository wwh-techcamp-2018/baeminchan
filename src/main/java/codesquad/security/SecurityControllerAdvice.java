package codesquad.security;

import codesquad.exception.LoginFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SecurityControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void loginFailed() {
        log.debug("[LoginFailedException] 로그인을 실패했습니다.");
    }
}
