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
        Long userId = userService.save(userDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users/" + userId));
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto, HttpSession session) throws UnAuthenticationException {
        User user = userService.login(loginDto);
        HttpSessionUtils.setUserSessionKey(session, user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        HttpSessionUtils.removeSessionID(session);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.OK);
    }

}
