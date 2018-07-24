package codesquad.web;

import codesquad.domain.UserRepository;
import codesquad.exception.UnAuthenticationException;
import codesquad.security.HttpSessionUtils;
import codesquad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(String userId, String password, HttpSession session) throws UnAuthenticationException {
        log.debug("userId : {}, password : {}", userId, password);
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, userService.login(userId, password));

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
    }

}
