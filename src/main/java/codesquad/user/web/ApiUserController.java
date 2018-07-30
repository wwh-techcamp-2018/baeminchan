package codesquad.user.web;

import codesquad.RestResponse;
import codesquad.user.domain.User;
import codesquad.user.dto.UserLoginDto;
import codesquad.user.dto.UserSignupDto;
import codesquad.user.service.UserService;
import codesquad.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<RestResponse<?>> create(@Valid @RequestBody UserSignupDto dto) {
        User user = userService.add(dto.toEntity());

        return ResponseEntity.created(URI.create(String.format("/api/users/%s", user.getUuid())))
                .body(RestResponse.success());
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponse<?>> login(@Valid @RequestBody UserLoginDto dto, HttpSession session) {
        User loginUser = userService.login(dto.toEntity());
        session.setAttribute(SessionUtil.USER_SESSION_KEY, loginUser);

        return ResponseEntity.ok().body(RestResponse.success());
    }
}
