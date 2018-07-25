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
        log.debug("[가입 시도] email: {} password: {} passwordConfirm: {} name: {} phoneNo: {}",
                joinUserDTO.getEmail(), joinUserDTO.getPassword(), joinUserDTO.getPasswordConfirm(),
                joinUserDTO.getName(), joinUserDTO.getPhoneNo());
        User loginUser = userService.login(userService.join(joinUserDTO));
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, loginUser);
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
        log.debug("[로그인 시도] email: {} password: {}", loginUserDTO.getEmail(), loginUserDTO.getPassword());
        User loginUser = userService.login(loginUserDTO);
        log.debug("[로그인 성공] email: {} 응답 코드: 200 OK", loginUserDTO.getEmail());
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, loginUser);
        // TODO: 2018. 7. 24. SetLocation을 통해서 여기서 redirect URI를 지정해줄 수 있지 않을까?
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}