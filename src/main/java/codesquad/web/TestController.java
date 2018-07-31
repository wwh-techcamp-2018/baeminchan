package codesquad.web;

import codesquad.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/test", "/api/admin"})
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("test")
    public String Test(HttpSession session){
        log.debug("testLoginUser : {}", session.getAttribute(SessionUtil.SESSION_KEY));
        return "redirect:/";
    }

    @GetMapping("api/admin")
    public String adminTest(HttpSession session) {
        log.debug("adminLoginUser : {}", session.getAttribute(SessionUtil.SESSION_KEY));
        return "redirect:/";
    }
}
