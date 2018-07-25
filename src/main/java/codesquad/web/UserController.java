package codesquad.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/signup")
    public String signup() {
        return "/user/join";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
}
