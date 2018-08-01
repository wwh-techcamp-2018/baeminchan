package codesquad.admin.web;

import codesquad.domain.Category;
import codesquad.dto.UserDto;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String list(Model model) {
        List<Category> categories = categoryService.getList(null);
        model.addAttribute("categories", categories);
        return "/admin/category-list2";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryService.getList(null);
        model.addAttribute("categories", categories);
        return "/admin/category-create";
    }


}
