package codesquad.web;

import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getRootCategories());
        return "/index";
    }
}
