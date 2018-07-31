package codesquad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/categories")
    public String list() {
        return "/admin/category";
    }
}
