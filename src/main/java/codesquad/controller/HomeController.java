package codesquad.controller;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
public class HomeController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/")
    public String home() {
        return "/index";
    }
}
