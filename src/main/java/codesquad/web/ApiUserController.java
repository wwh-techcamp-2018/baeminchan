package codesquad.web;

import codesquad.domain.User;
import codesquad.service.UserService;
import codesquad.support.ErrorResponse;
import codesquad.support.JsonResponse;
import codesquad.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);
    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@Valid @RequestBody User user){
        User savedUser = userService.create(user);
        log.debug("savedUser : {}", savedUser);
        return new JsonResponse("/");
    }

    @PostMapping("/login")
    public JsonResponse login(@RequestBody Map<String, String> all, HttpSession session) {
        log.debug("User Loginning : {} {}",all.toString());
        session.setAttribute(SessionUtil.SESSION_KEY, userService.login(all.get("email"), all.get("password")));
        return new JsonResponse("/");
    }

}
