package codesquad.web;

import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String rootCategoryForm() {
        return "/admin/category";
    }

    @GetMapping("/{id}")
    public String categoryForm(@PathVariable Long id) {
        categoryService.category(id);
        return "/admin/category";
    }
}
