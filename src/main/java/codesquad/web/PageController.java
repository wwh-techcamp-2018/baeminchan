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
}
