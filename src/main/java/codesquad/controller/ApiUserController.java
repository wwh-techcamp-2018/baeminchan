package codesquad.controller;

import codesquad.domain.User;
import codesquad.dto.UserDto;
import codesquad.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

import static codesquad.security.HttpSessionUtils.SESSIONED_USER;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> join(@Valid @RequestBody UserDto userDto) {
        userService.join(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, String> newUser, HttpSession session) throws IllegalAccessException {
        User loginUser = userService.login(newUser.get("userId"), newUser.get("password"));
        session.setAttribute(SESSIONED_USER, loginUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
