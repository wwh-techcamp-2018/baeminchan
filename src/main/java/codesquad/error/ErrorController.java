package codesquad.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorController {

    @GetMapping("")
    public String errorPage() {
        return "/error/error403";
    }
}
