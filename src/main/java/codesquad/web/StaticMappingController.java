package codesquad.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StaticMappingController {
    private static final Logger log = LoggerFactory.getLogger(StaticMappingController.class);
    @GetMapping("")
    public String home() {
        log.debug("gigigi12gi");
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("join")
    public String join() {
        return "join";
    }
}
