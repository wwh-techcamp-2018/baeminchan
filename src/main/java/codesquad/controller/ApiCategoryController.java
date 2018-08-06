package codesquad.controller;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Iterable<Category> list() {
        return categoryService.getParentList();
    }
}
