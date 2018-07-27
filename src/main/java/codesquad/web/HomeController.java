package codesquad.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "/index";
    }
    @GetMapping("/join")
    public String join() {
        return "/join";
    }
    @GetMapping("/login")
    public String login() {
        return "/login";
    }
}
