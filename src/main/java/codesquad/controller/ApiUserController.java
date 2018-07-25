package codesquad.controller;

import codesquad.domain.User;
import codesquad.dto.UserDto;
import codesquad.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);
    private static final String SESSIONED_USER = "sessionedUser";
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> join(@Valid @RequestBody UserDto userDto) {
        log.info("userDto : {}", userDto);
        User user = userService.join(userDto);

        return new ResponseEntity(new HttpHeaders(), HttpStatus.CREATED);
//        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, String> newUser, HttpSession session) throws IllegalAccessException {
        User loginUser = userService.login(newUser.get("userId"), newUser.get("password"));
        session.setAttribute(SESSIONED_USER, loginUser);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
    }
}
