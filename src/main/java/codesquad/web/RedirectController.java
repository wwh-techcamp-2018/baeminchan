package codesquad.web;

import codesquad.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectController {

    @GetMapping("/users/login")
    public String login(HttpSession session) {
        if (SessionUtil.isLoginUser(session)) return "redirect:/";
        return "/login";
    }

    @GetMapping("/users/signup")
    public String signup(HttpSession session) {
        if (SessionUtil.isLoginUser(session)) return "redirect:/";
        return "/join";
    }

    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/categories")
    public String categories() {
        return "/categories";
    }
}
