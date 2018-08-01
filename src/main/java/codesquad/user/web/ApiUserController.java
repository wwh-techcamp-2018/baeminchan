package codesquad.user.web;

import codesquad.user.domain.User;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.UserDto;
import codesquad.exception.UnAuthenticationException;
import codesquad.security.HttpSessionUtils;
import codesquad.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/users")
public class ApiUserController {

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto userDto) throws UnAuthenticationException {
        User user = userService.save(userDto);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto, HttpSession session) throws UnAuthenticationException {
        User user = userService.login(loginDto);
        HttpSessionUtils.setUserSessionKey(session, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        HttpSessionUtils.removeSessionID(session);
        return ResponseEntity.ok().build();
    }

}
