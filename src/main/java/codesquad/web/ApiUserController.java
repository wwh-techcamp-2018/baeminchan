package codesquad.web;

import codesquad.domain.User;
import codesquad.dto.JoinUserDTO;
import codesquad.dto.LoginUserDTO;
import codesquad.security.HttpSessionUtils;
import codesquad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> join(@Valid @RequestBody JoinUserDTO joinUserDTO, HttpSession session) {
        log.debug("[가입 시도] {}", joinUserDTO.toString());
        User loginUser = userService.login(userService.join(joinUserDTO));
        HttpSessionUtils.saveSession(session, loginUser);
        log.debug("[가입 성공] email: {} 응답 코드: 200 OK", joinUserDTO.getEmail());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> list() {
        return null;
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginUserDTO loginUserDTO, HttpSession session) {
        log.debug("[로그인 시도] {}", loginUserDTO.toString());
        User loginUser = userService.login(loginUserDTO);
        log.debug("[로그인 성공] email: {} 응답 코드: 200 OK", loginUserDTO.getEmail());
        HttpSessionUtils.saveSession(session, loginUser);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}