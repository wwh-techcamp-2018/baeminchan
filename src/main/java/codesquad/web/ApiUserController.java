package codesquad.web;

import codesquad.domain.User;
import codesquad.dto.JoinUserDTO;
import codesquad.dto.LoginUserDTO;
import codesquad.security.HttpSessionUtils;
import codesquad.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "회원가입", notes = "회원정보를 입력하여 회원가입 시도 ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "삽입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("")
    public ResponseEntity<Void> join(@Valid @RequestBody JoinUserDTO joinUserDTO, HttpSession session) {
        log.debug("[가입 시도] {}", joinUserDTO.toString());
        User loginUser = userService.login(userService.join(joinUserDTO));
        HttpSessionUtils.saveSession(session, loginUser);
        log.debug("[가입 성공] email: {} 응답 코드: 200 OK", joinUserDTO.getEmail());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "로그아웃", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "로그아웃 성공")
    })
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "로그인", notes = "email, password를 이용하여 로그인 시도")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginUserDTO loginUserDTO, HttpSession session) {
        log.debug("[로그인 시도] {}", loginUserDTO.toString());
        User loginUser = userService.login(loginUserDTO);
        log.debug("[로그인 성공] email: {} 응답 코드: 200 OK", loginUserDTO.getEmail());
        HttpSessionUtils.saveSession(session, loginUser);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}