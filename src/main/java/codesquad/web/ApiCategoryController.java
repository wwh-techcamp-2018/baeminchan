package codesquad.web;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/categories")
public class ApiCategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("")
    public void save(@Valid @RequestBody Category category) {
        categoryService.save(category);
    }
}
