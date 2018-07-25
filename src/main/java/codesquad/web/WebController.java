package codesquad.web;

import codesquad.support.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (SessionUtil.isLoginUser(session)) {
            return "/";
        }

        return "/login";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session) {
        if (SessionUtil.isLoginUser(session)) {
            return "/";
        }

        return "/join";
    }

    @GetMapping("/")
    public String index() {
        return "/";
    }
}
