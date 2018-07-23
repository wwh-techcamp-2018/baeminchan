package codesquad.controller;

import codesquad.domain.User;
import codesquad.dto.JoinUserDto;
import codesquad.dto.LoginUserDto;
import codesquad.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Resource(name = "userService")
    UserService userService;

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody JoinUserDto joinUserDto){
        //TODO 유효성 검사
        userService.add(joinUserDto);
        return ResponseEntity.created(URI.create("/api/users")).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(HttpSession session, @RequestBody LoginUserDto loginUserDto){
        //TODO 세션키 바꾸기
        session.setAttribute("KEY",userService.login(loginUserDto));
        return ResponseEntity.ok(null);
    }


}
