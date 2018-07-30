package codesquad.controller;

import codesquad.domain.Category;
import codesquad.security.AdminAuthInterceptor;
import codesquad.security.HttpSessionUtils;
import codesquad.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private CategoryService categoryService;

    @GetMapping("/")
    public String home(HttpSession session) {
        log.info("home : {}", session.getAttribute(HttpSessionUtils.SESSIONED_USER));
        return "/index";
    }
}
