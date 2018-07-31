package codesquad.web;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> list() {
        return categoryService.getCategories();
    }

}
