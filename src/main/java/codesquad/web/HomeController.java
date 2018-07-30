package codesquad.web;

import codesquad.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/")
    public String home(HttpSession session) {
        log.info("--------------msg : {}", session.getAttribute(UserService.USER_SESSION_KEY));
        return "/index";
    }
    @GetMapping("/page/login")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/page/join")
    public String join(){
        return "/user/join";
    }
}
