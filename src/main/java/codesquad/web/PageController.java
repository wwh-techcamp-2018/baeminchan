package codesquad.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/page/join")
    public String join() {
        return "/user/join";
    }

    @GetMapping("/page/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/page/admin/login")
    public String adminLogin() {
        return "/admin/login";
    }

    @GetMapping("/page/admin")
    public String adminIndex() { return "/admin/index"; }
}
