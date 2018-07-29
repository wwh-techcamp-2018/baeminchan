package codesquad.user.web;

import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.UserDto;
import codesquad.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/users")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto newUserDto) {
        User user = userService.save(newUserDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users" + user.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto, HttpSession httpSession) {
        log.debug("login form: {}", loginDto);
        User user = userService.login(loginDto);
        httpSession.setAttribute(UserService.USER_SESSION_KEY, user.getId());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {
        userService.logout(httpSession);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
