package codesquad.controller;

import codesquad.domain.user.User;
import codesquad.dto.user.JoinUserDto;
import codesquad.dto.user.LoginUserDto;
import codesquad.service.UserService;
import codesquad.util.SessionUtil;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Resource(name = "userService")
    UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원정보를 입력하여 회원가입 시도 ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "삽입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("")
    public ResponseEntity<User> create(@Valid @RequestBody JoinUserDto joinUserDto, HttpSession session) {
        session.setAttribute(SessionUtil.SESSTION_KEY, userService.add(joinUserDto));
        return ResponseEntity.created(URI.create("/api/users")).body(null);
    }


    @ApiOperation(value = "로그인", notes = "아이디, 비밀번호를 입력하여 로그인하였습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삽입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("/login")
    public ResponseEntity<User> login(HttpSession session, @RequestBody LoginUserDto loginUserDto) {
        session.setAttribute(SessionUtil.SESSTION_KEY, userService.login(loginUserDto));
        return ResponseEntity.ok(null);
    }


}
