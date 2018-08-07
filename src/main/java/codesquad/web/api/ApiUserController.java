package codesquad.web.api;

import codesquad.dto.LoginDto;
import codesquad.dto.SignupDto;
import codesquad.service.UserService;
import codesquad.util.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class ApiUserController {

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupDto signupDto) {
        userService.signup(signupDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginDto loginDto, HttpSession session) {
        SessionUtil.setUserSession(session, userService.login(loginDto));
    }
}
