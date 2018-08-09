package codesquad.controller;

import codesquad.domain.user.NormalUser;
import codesquad.dto.user.JoinUserDto;
import codesquad.dto.user.LoginUserDto;
import codesquad.service.UserService;
import codesquad.util.SessionUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Resource(name = "userService")
    UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원정보를 입력하여 회원가입 시도합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "삽입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("")
    public ResponseEntity<NormalUser> create(@Valid @RequestBody JoinUserDto joinUserDto, HttpSession session) {
        session.setAttribute(SessionUtil.SESSION_KEY, userService.add(joinUserDto));
        return ResponseEntity.created(URI.create("/api/users")).body(null);
    }


    @ApiOperation(value = "로그인", notes = "아이디, 비밀번호를 입력하여 로그인합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "로그인 실패")
    })
    @PostMapping("/login")
    public ResponseEntity<NormalUser> login(HttpSession session, @RequestBody LoginUserDto loginUserDto) {
        session.setAttribute(SessionUtil.SESSION_KEY, userService.login(loginUserDto).toUserSessionDto());
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "로그아웃 성공"),
            @ApiResponse(code = 400, message = "로그아웃 실패")
    })
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute(SessionUtil.SESSION_KEY);
        return ResponseEntity.ok(null);
    }

}
