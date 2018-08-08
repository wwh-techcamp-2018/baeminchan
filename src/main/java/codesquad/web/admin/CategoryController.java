package codesquad.web.admin;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String list(Model model) {
        List<Category> categories = categoryService.getList(null);
        model.addAttribute("categories", categories);
        return "/admin/category-list";
    }
}
